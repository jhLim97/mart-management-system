package com.company.Controller;

import com.company.Model.OrderDAO;
import com.company.Model.OrderDTO;
import com.company.Model.OrderHistoryDAO;
import com.company.Model.OrderHistoryDTO;
import com.company.View.ShoppingView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;


public class OrderController {

    ShoppingView shoppingView;
    OrderHistoryDTO orderHistoryDTO;
    OrderHistoryDAO orderHistoryDAO;
    OrderDTO orderDTO;
    OrderDAO orderDAO;
    public boolean status = true; // 물품 재고 여부 판단해 구매 가능 및 주문, 주문 히스토리에 넣을 지 여부 결정
                                  // 아니면 product controller에서 제어한 다음 구매 가능여부만 ㄴshoppingframe에 표기하면 될듯?..

    public OrderController(ShoppingView shoppingView) {
        this.shoppingView = shoppingView;
        this.shoppingView.addOrderActionListner(new OrderActionListener());
        orderDAO = new OrderDAO();
        orderHistoryDAO = new OrderHistoryDAO();
    }

    public class OrderActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();

            if(obj == shoppingView.btnPay) {

                int rowCount = shoppingView.tbMyList.getRowCount(); // myList에 담긴 물품 즉, 행의 개수 가져오기
                int tmp = rowCount;
                int orderCode, prCode, prPrice, prCount;
                orderCode = 0; // 초기화

                String cName[], cPhone[], prName;
                cName = shoppingView.lblCname.getText().split(" : "); // 이름 추출
                cPhone = shoppingView.lblCphoneNum.getText().split(" : "); // 번호 추출

                // ----- 현재 시간 가져오기 -----
                Date now = new Date();
                Calendar cal = Calendar.getInstance();

                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1; // 1월이 0을 반환하므로 +1
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int min = cal.get(Calendar.MINUTE);
                int sec = cal.get(Calendar.SECOND);

                //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss");
                //String strDate = sdf.format(cal.getTime());
                //System.out.println(strDate);

                orderDTO = new OrderDTO(); // 먼저 주문 테이블에 총 가격을 제외하고 작성
                //orderDTO.setEntry_price();
                orderDTO.setC_name(cName[1]);
                orderDTO.setPhone_num(cPhone[1]);
                orderDTO.setBuy_date(year + "-" + month + "-" + day);

                int entryPrice = 0; // 주문 내역 테이블에 물품 다 추가한 후 해당 정보를 이용해 주문테이블 업데이트

                // 여기서 물품 개수 미달이면 적절히 처리해줘야 할듯... --> 제어해서 주문 될 지 안될지 결정
                if(status){
                    if(orderDAO.addOrder(orderDTO)) {
                        System.out.println("주문 테이블 작성 완료!!"); // 콘솔창에 완료 여부 출력
                        orderCode = orderDTO.getOrder_code(); // last_insert_id()을 통해 얻어온 값
                    }
                    else {
                        System.out.println("주문 테이블 작성 실패..."); // 콘솔창에 완료 여부 출력
                        status = false; // 제어
                    }

                }

                while(rowCount != 0) {
                    prCode = Integer.parseInt((String)(shoppingView.tbMyList.getValueAt(rowCount-1,1))); // 물품을 넣는 순서를 역순으로 넣음
                    prName = (String)(shoppingView.tbMyList.getValueAt(rowCount-1,2)); // 상품이름도 히스토리 테이블에 칼럼 추가해서 넣으면 직관적으로 좋을듯?
                    prCount = Integer.parseInt((String)(shoppingView.tbMyList.getValueAt(rowCount-1,4)));
                    prPrice = Integer.parseInt((String)(shoppingView.tbMyList.getValueAt(rowCount-1,3)));


                    orderHistoryDTO = new OrderHistoryDTO();
                    orderHistoryDTO.setOrder_code(orderCode);
                    orderHistoryDTO.setPr_code(prCode);
                    orderHistoryDTO.setPr_count(prCount);
                    orderHistoryDTO.setPr_price(prCount*prPrice);

                    // 여기서 물품 개수 미달이면 적절히 처리해줘야 할듯... --> 제어해서 주문 될 지 안될지 결정
                    if(status){
                        if(orderHistoryDAO.addOrderHistory(orderHistoryDTO)) {
                            System.out.println("주문 완료!!"); // 콘솔창에 완료 여부 출력
                            entryPrice += prCount*prPrice;
                        }
                        else System.out.println("주문 실패..."); // 콘솔창에 완료 여부 출력
                    }

                    rowCount--;
                }

                for (int i = tmp - 1; i >= 0; i--) { // 구매완료 시 MyList 내역 삭제
                    shoppingView.modelMyList.removeRow(i);
                }

                orderDTO.setEntry_price(entryPrice);
                if(orderDAO.updateOrder(orderDTO)) System.out.println("주문 테이블 업데이트 완료!!"); // 콘솔창에 완료 여부 출력
                else System.out.println("주문 테이블 업데이트 실패..."); // 콘솔창에 완료 여부 출력
            }
        }
    }

    public static void main(String[] args) {
        new OrderController(new ShoppingView());
    }

}
