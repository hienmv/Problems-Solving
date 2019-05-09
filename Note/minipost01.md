# Cấu trúc dữ liệu và thuật toán cơ bản

Xin chào các bạn!
Đây là bài viết đầu tiên của mình về chủ đề cấu trúc dữ liệu và thuật toán cơ bản. Thực chất chỉ là ghi chép cá nhân của mình trong việc ôn tập, nâng cao kỹ năng giải quyết vấn đề trong lập trình, nên khó tránh khỏi những sai sót, rất mong nhận được nhiều sự đóng góp ý kiến, chia sẻ của các bạn. 

### Bài viết gồm 3 phần
- Tầm quan trọng của việc nắm vững cấu trúc dữ liệu và thuật toán.
- Những kiến thức cần tìm hiểu về cấu trúc dữ liệu và thuật toán cơ bản. 
- Ví dụ về việc áp dụng kiến thức về cấu trúc dữ liệu và thuật toán trong một vấn đề thực tế.

# Tầm quan trọng của thuật toán.

Có ba miền kiến thức quan trọng, mà mỗi chúng ta cần phải nắm vững, để có thể trở thành một Software Engineer (SE) giỏi:
 - Kiến thức căn bản: cấu trúc dữ liệu và thuật toán, lập trình hướng đối tượng, TCP/IP, HTTP, Process and Thread...
 - Kiến thức theo từng domain (Web Development, Mobile Development, Embedded System..) mà bạn muốn theo đuổi.
 - Kiến thức phân tích, thiết kế hệ thống.

Trong bài viết này, mình xin đề cập kiến thức về cấu trúc dữ liệu và thuật toán nằm ở nội dung thứ nhất - kiến thức căn bản.

![algorithms](https://github.com/hienmv/Problems-Solving/blob/master/Note/algorithms.jpg) 

>Thuật toán đã lên lỏi tới từng ngõ ngách trong cuộc sống: từ hệ thống tài chính, ngân hàng, hệ thống đèn giao thông, smartphone... (Theo George Dvorsky, Editor at Gizmodo). 

Việc bạn có thể tìm kiếm nhanh được thông tin trên Google Search, hay bạn được gợi ý các sản phẩm khi mua sắm online, hay gợi ý video khi bạn xem video trên Youtube...tất cả những tính năng này hoạt động hiệu quả là nhờ việc các công ty này áp dụng mạnh mẽ các thuật toán. Có thể nói, thuật toán là nền tảng phát triển của các công ty công nghệ như Google, Facebook, Amazon... 

Ngay cả với việc xử lý một thao tác cơ bản của hệ thống như: lấy dữ liệu từ Database kèm theo nhiều điều kiện ràng buộc, sau đó hiển thị lên màn hình để cho người dùng thao tác và xử lý, ngoài việc cần nắm vững kiến thức về ngôn ngữ mà chúng ta đang sử dụng, chúng ta cần vận dụng các hiểu biết về cấu trúc dữ liệu và giải thuật, để xác định việc sử dụng cấu trúc dữ liệu nào là phù hợp với từng yêu cầu cụ thể về bộ nhớ, thời gian thực thi...

Việc nắm vững kiến thức về cấu trúc dữ liệu và thuật toán giúp chúng ta nhanh chóng tìm ra hướng giải quyết vấn đề, cũng như đánh giá được thời gian xử lý, dung lượng sử dụng của source code, từ đó có thể nâng cao chất lượng của hệ thống.
 
# Những kiến thức cần tìm hiểu về cấu trúc dữ liệu và giải thuật căn bản.
Đối với mỗi ngôn ngữ, các cấu trúc dữ liệu có thể được implement theo nhiều cách khác nhau, nhưng chúng đều có cùng ý nghĩa chung.
Dưới đây mình xin liệt kê một số kiến thức cấu trúc dữ liệu và thuật toán cơ bản:
1. Mảng động (ví dụ: vector trong C++, ArrayList trong Java...)
2. Các thuật toán về sorting, stack, queue.
3. Thuật toán về đồ thị, BFS, DFS, Dijkstra.
4. Cấu trúc dữ liệu và giải thuật liên quan tới Tree như Binary Search, Binary Search Tree.
5. Cấu trúc dữ liệu nâng cao, cấu trúc cây tiền tố. 

## Bạn có thể học những kiến thức trên ở đâu ?
Bạn hoàn toàn có thể tự học được những kiến thức cơ bản trên. 
Mình xin phép liệt kê một số nguồn tài liệu, khoá học mà mình thấy rất hiệu quả:
### Tiếng việt
* [VNOI](http://vnoi.info) - Diễn đàn tin học, thuật toán Việt nam. Đây là một nguồn cực kỳ bổ ích cho các bạn muốn nâng cao về thuật toán.
* [BigOCoding](http://bigocoding.com) - Học thuật toán với chuyên gia. Đây là trung tâm chuyên dạy về thuật toán, vì vậy sẽ mất phí, đổi lại chất lượng giảng dạy rất tốt, và người đứng lớp có kinh nghiệm, nhiệt tình.
### Tiếng anh
* [MIT - Introduction to Algorithms](https://www.youtube.com/watch?v=HtSuA80QTyo&list=PLUl4u3cNGP61Oq3tWYp6V_F-5jb5L2iHb) - Chuỗi video giới thiệu thuật toán của trường đại học MIT danh tiếng.
* [Coursea - Algorithms Specialization](https://www.coursera.org/specializations/algorithms) - Chuỗi khoá học về thuật toán của trường đại học Stanford.
### Một số sách về thuật toán. 
* [Cracking the Coding Interview: 189 Programming Questions and Solutions](https://www.amazon.com/Cracking-Coding-Interview-Programming-Questions/dp/0984782850)
* [Algorithms, Java](https://www.amazon.com/Algorithms-4th-Robert-Sedgewick/dp/032157351X/)
* [Data Structures and Algorithms in Python](https://www.amazon.com/Structures-Algorithms-Python-Michael-Goodrich/dp/1118290275/)
### Còn nếu bạn muốn thử sức giải các bài tập liên quan tới thuật toán
* [hackerrank](https://www.hackerrank.com)
* [codeforces](https://codeforces.com)
* [Sphere online judge](https://www.spoj.com)

# Ví dụ về việc áp dụng kiến thức về cấu trúc dữ liệu và thuật toán trong một vấn đề thực tế.
#### Tình huống thực tế: 
Team của bạn được giao nhiệm vụ phát triển một mini game trên mobile.
Nội dung của game như sau: cho một bản đồ 2 chiều (6x11). Trên bản đồ có 9 ô chứa phần quà. Người chơi sẽ tung xúc xắc để xác định số bước có thể di chuyển, với mục tiêu là lấy được nhiều phần quà nhất có thể trong thời gian cho phép.

Lưu ý: 
- 9 phần quà được sắp xếp ngẫu nhiên, sao cho **không** có 3 phần quà nào cùng nằm trong 1 ma trận 2 chiều 3x3.
- Người dùng chỉ có thể di chuyển ngang, hoặc dọc. 
- Vị trí xuất phát: ô dưới cùng bên phải.

Nhiệm vụ của bạn là xây dựng bản đồ trên (bản đồ có 9 phần quà được sắp xếp ngẫu nhiên).
Đơn giản hoá vấn đề đi, nhiệm vụ của bạn là tạo ra 1 mảng 2 chiều 6x11, giá trị mỗi vị trí là 0 hoặc 1, với giá trị 1 - tại vị trí đó có phần quà. Mảng kết quả gồm có 9 vị trí có giá trị là 1, và được sắp xếp ngẫu nhiên, sao cho không có 3 vị trí nào nằm trong 1 mảng 2 chiều 3x3.

Các bạn suy nghĩ thử xem sao nhé.

![bản đồ](https://github.com/hienmv/Problems-Solving/blob/master/Note/seeds9of611.png)

#### Đây là hướng giải quyết của mình:
**Nhận xét**: có 2 yếu tố cần đảm bảo:
- Tính ngẫu nhiên trong việc sắp xếp 9 phần quà.
- Không có 3 phần quà nào mà chúng nằm trong một mảng 2 chiều 3x3.

1. Yếu tố thứ nhất: tính ngẫu nhiên trong việc sắp xếp 9 phần quà.

Nếu chúng ta random 2 giá trị toạ độ x,y để lấy ngẫu nhiên 1 vị trí bất kỳ trên bản đồ, và sau đó kiểm tra xem điểm đó có thoả mãn yếu tố thứ hai ở trên hay không, thì sẽ dẫn đến việc: có thể random lại chính giá trị đã được random trước đó, điều này có thể lặp nhiều lần, và trong trường hợp xấu nhất là lặp vô hạn.

Để giải quyết vấn đề trên, thay vì random theo từng giá trị toạ độ x,y, chúng ta có thể tạo ra 1 danh sách các điểm trên bản đồ,
sau đó trộn danh sách này để có được 1 danh sách các điểm được sắp xếp ngẫu nhiêu. -> đảm bảo được tính ngẫu nhiên.

Duyệt danh sách này, mỗi lần lấy ra 1 điểm, và kiểm tra điểm này có thoả mãn yếu tố thứ hai ở trên, thì cho vào danh sách kết quả. Việc duyệt danh sách này, đảm bảo được việc chỉ xét 1 điểm duy nhất 1 lần, nên độ phức tạp theo thời gian ở đây là O(6x11).

Dưới đây là mã nguồn minh hoạ bằng Java.
```java
int w = 11;
int h = 6;
int maxPointNum = 9;
// create a list of Points
ArrayList<Point> pointList = new ArrayList<>(w*h);
for(int x=0; x < w; ++x) {
    for (int y=0; y < h; ++y) {
        pointList.add(new Point(x,y));
    }
}
// shuffle the list of Points to get the list point with random order.
Collections.shuffle(pointList);
```

2. Yếu tố thứ hai: không có 3 phần quà nào mà chúng nằm trong một mảng 2 chiều 3x3.

Để kiểm tra xem điểm đang được xét theo cách trên, có thoả mãn điều kiện này hay không, ta cần phải kiểm tra điểm này với 2 điểm bất kỳ trong danh sách kết quả, xem chúng có nằm trong 1 mảng 2 chiều 3x3 hay không. 

Tuy nhiên, chúng ta không nhất thiết phải kiểm tra 2 điểm bất kỳ trong danh sách kết quả, mà chỉ cần kiểm tra các điểm lân cận với điểm đang được xét, đã có 2 điểm nào mà đã được chọn hay chưa mà thôi. Chúng ta có thể sử dụng map, với bộ dữ liệu: key - toạ độ x, value - điểm, lưu trữ danh sách điểm đã được chọn, để có thể dễ dàng so sánh điểm đang được xét với các điểm lân cận.

Dưới đây là mã nguồn minh hoạ bằng Java.
```java
static boolean isInvalidPoint(Point p, HashMap<Integer, ArrayList<Point>> mapX) {
  int i = p.x - 2;
  if (i < 0) i = 0;
  int count = 0;
  for (; i < p.x + 3; ++i) {
      if (mapX.containsKey(i)) {
          for(Point point : mapX.get(i)) {
              if ( Math.abs(point.y - p.y) < 3) {
                  count++;
              }
          }
          if (count > 1) return true;
      }
  }
  return false;
}
```
Với cách tiếp cận này, độ phức tạp thuật toán theo thời gian là O(n), theo không gian là O(n).

Bạn có thể tham khảo lời giải đầy đủ của mình cho bài này tại [đây](https://github.com/hienmv/Problems-Solving/blob/master/Java/Seeds9Of611.java). 

**Kết luận**

Như vậy, mình đã giới thiệu lại cho các bạn về thuật toán cơ bản, cũng như trích dẫn một số nguồn hữu ích để các bạn có thể rèn luyện, nâng cao kỹ năng giải quyết vấn đề trong lập trình. Rất hi vọng nhận được nhiều đóng góp ý kiến của các bạn. Hẹn gặp các bạn ở bài viết tiếp theo.

Happy coding!

#### Bài viết có sự tham khảo từ: 
* [SERIES PHẢN PHÁC QUI CHÂN – HỌC THUẬT TOÁN ĐỂ LÀM VẸO GÌ???](https://toidicodedao.com/2016/10/06/hoc-thuat-toan-de-lam-gi/)
* [BIG-O BLUE: INTERMEDIATE ALGORITHMS](http://bigocoding.com/khoa-hoc-dang-mo-blue/)
