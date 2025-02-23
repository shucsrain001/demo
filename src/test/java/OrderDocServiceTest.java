//import com.demo.es.OrderDoc;
//import com.demo.es.OrderDocService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;


public class OrderDocServiceTest {

//    @Autowired
//    private OrderDocService orderDocService;
//
//    @Test
//    public void saveAll() {
//        orderDocService.saveAll(Arrays.asList(
//                new OrderDoc("1",100000l,"苹果电脑"),
//                new OrderDoc("2",100000l,"苹果电脑"),
//                new OrderDoc("3",100000l,"苹果电脑"),
//                new OrderDoc("4",100000l,"苹果电脑"),
//                new OrderDoc("5",100000l,"苹果电脑"),
//                new OrderDoc("6",100000l,"苹果电脑"),
//                new OrderDoc("7",100000l,"苹果电脑")
//        ));
//    }
//
//
//    @Test
//    public void findById() {
//
//
//        System.out.println(orderDocService.findById("1"));
//    }

//    @Test
//    public void deleteById() {
//
//
//        orderDocService.deleteById("1");
//    }
//
//    @Test
//    public void updateById() {
//
//
//        OrderDoc orderDoc = orderDocService.findById("1");
//        orderDoc.setTitle("华为电脑");
//        orderDocService.updateById(orderDoc);
//    }
//
//    @Test
//    public void findList() {
//
//
//        OrderDoc orderDoc = new OrderDoc(null, 100l, "电脑");
//        PageResponse<OrderDoc> response = orderDocService.findList(orderDoc, 0, 10);
//        System.out.println(response.getTotal());
//        response.getList().forEach(System.out::println);
//
//    }
//
//    @Test
//    public void findAll() {
//
//
//        PageResponse<OrderDoc> response = orderDocService.findAll( 0, 10);
//        System.out.println(response.getTotal());
//        response.getList().forEach(System.out::println);
//    }
//
//    @Test
//    public void findHighlight() {
//
//
//        OrderDoc orderDoc = new OrderDoc(null, 100l, "电脑",null);
//        PageResponse<OrderDoc> response = orderDocService.findHighlight( orderDoc,0, 10);
//        System.out.println(response.getTotal());
//        response.getList().forEach(System.out::println);
//    }
}