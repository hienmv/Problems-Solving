# Cấu trúc dữ liệu và thuật toán cơ bản

Xin chào các bạn!
Bài viết đầu tiên của mình về chủ đề cấu trúc dữ liệu và thuật toán cơ bản. Đây thực chất chỉ là ghi chép cá nhân của mình trong việc ôn tập, nâng cao kỹ năng giải quyết vấn đề trong lập trình, nên không thể tránh được nhiều sai sót, rất mong nhận được nhiều sự đóng góp ý kiến, chia sẻ của các bạn. 

Bài viết gồm 3 phần.
- Tầm quan trọng của việc nắm vững cấu trúc dữ liệu và thuật toán trong việc giải quyết vấn đề trong tin học.
- Những kiến thức cần tìm hiểu về cấu trúc dữ liệu và thuật toán cơ bản. 
- Ví dụ về việc áp dụng kiến thức về cấu trúc dữ liệu và thuật toán trong một vấn đề thực tế.

# Tầm quan trọng của thuật toán trong việc giải quyết vấn đề trong tin học.

Có ba miền kiến thức quan trọng, mà mỗi chúng ta cần phải nắm vững, để có thể trở thành một Software Engineer (SE) giỏi:
 - Kiến thức căn bản: cấu trúc dữ liệu và thuật toán, lập trình hướng đối tượng, TCP/IP, HTTP, Process and Thread...
 - Kiến thức theo từng domain (Web Development, Mobile Development, Embedded System..) mà bạn muốn theo đuổi.
 - Kiến thức phân tích, thiết kế hệ thống.

Trong bài viết này, mình xin đề cập kiến thức về cấu trúc dữ liệu và thuật toán nằm ở nội dung thứ nhất - kiến thức căn bản. 
Việc nắm vững kiến thức về cấu trúc dữ liệu và thuật toán là rất quan trọng trong việc giải quyết vấn đề. Việc bạn có thể tìm kiếm nhanh được thông tin trên Google Search, hay bạn được gợi ý các sản phẩm khi mua sắm online, hay gợi ý video khi bạn xem video trên Youtube... tất cả những tính năng này hoạt động hiệu quả là nhờ việc các công ty này áp dụng mạnh mẽ các thuật toán. 

Đối với lập trình viên, việc xử lý một thao tác cơ bản của hệ thống như: lấy dữ liệu từ Database kèm theo nhiều điều kiện ràng buộc, sau đó hiển thị lên màn hình để cho người dùng thao tác và xử lý, không chỉ cần có kiến thức về ngôn ngữ mà chúng ta đang sử dụng, mà chúng ta còn cần vận dụng các hiểu biết về cấu trúc dữ liệu và giải thuật, để xác định việc sử dụng cấu trúc dữ liệu nào là phù hợp với từng yêu cầu cụ thể về bộ nhớ, thời gian thực thi...

Việc nắm vững kiến thức về cấu trúc dữ liệu và thuật toán giúp chúng ta nhanh chóng tìm ra hướng giải quyết vấn đề, cũng như đánh giá được thời gian xử lý, dung lượng sử dụng của source code, từ đó có thể nâng cao chất lượng của hệ thống.
 
# Những kiến thức cần tìm hiểu về cấu trúc dữ liệu và giải thuật căn bản.
Đối với mỗi ngôn ngữ, các cấu trúc dữ liệu có thể được implement khác nhau, nhưng đều có cùng ý nghĩa chung.
Dưới đây mình xin liệt kê một số kiến thức cấu trúc dữ liệu và thuật toán cơ bản:
1. Mảng động (ví dụ: vector trong C++, ArrayList trong Java...)
2. Các thuật toán về sorting, stack, queue.
3. Thuật toán về đồ thị, BFS, DFS, Dijkstra.
4. Cấu trúc dữ liệu và giải thuật liên quan tới Tree như Binary Search, Binary Search Tree.
5. Cấu trúc dữ liệu nâng cao, cấu trúc cây tiền tố. 

## Bạn có thể học những kiến thức trên ở đâu ?
Bạn hoàn toàn có thể tự học được những kiến thức cơ bản trên, vì có rất nhiều tài liệu, và khoá học mà bạn có thể tìm kiếm. 
Mình xin phép liệt kê một số nguồn tài liệu, khoá học mà mình thấy cực kỳ chất lượng:
### Tiếng việt
* [VNOI](http://vnoi.info) - Diễn đàn tin học, thuật toán Việt nam. Đây là một nguồn cực kỳ bổ ích cho các bạn muốn nâng cao về thuật toán.
* [BigOCoding](http://bigocoding.com) - Học thuật toán với chuyên gia. Đây là trung tâm chuyên dạy thuật toán, vì vậy sẽ mất phí :D, đổi lại chất lượng giảng dạy cực kỳ chất lượng.
### Tiếng anh
* [MIT - Introduction to Algorithms](https://www.youtube.com/watch?v=HtSuA80QTyo&list=PLUl4u3cNGP61Oq3tWYp6V_F-5jb5L2iHb) - Chuỗi videos giới thiệu thuật toán của trường đại học MIT danh tiếng.
* [Coursea - Algorithms Specialization](https://www.coursera.org/specializations/algorithms) Chuỗi khoá học về thuật toán của trường đại học Stanford.
### Sách về thuật toán. 
* [Cracking the Coding Interview: 189 Programming Questions and Solutions](https://www.amazon.com/Cracking-Coding-Interview-Programming-Questions/dp/0984782850)
* [Algorithms, Java](https://www.amazon.com/Algorithms-4th-Robert-Sedgewick/dp/032157351X/)
* [Data Structures and Algorithms in Python](https://www.amazon.com/Structures-Algorithms-Python-Michael-Goodrich/dp/1118290275/)

You can also:
  - Import and save files from GitHub, Dropbox, Google Drive and One Drive
  - Drag and drop markdown and HTML files into Dillinger
  - Export documents as Markdown, HTML and PDF

Markdown is a lightweight markup language based on the formatting conventions that people naturally use in email.  As [John Gruber] writes on the [Markdown site][df1]

> The overriding design goal for Markdown's
> formatting syntax is to make it as readable
> as possible. The idea is that a
> Markdown-formatted document should be
> publishable as-is, as plain text, without
> looking like it's been marked up with tags
> or formatting instructions.

This text you see here is *actually* written in Markdown! To get a feel for Markdown's syntax, type some text into the left window and watch the results in the right.

### Tech

Dillinger uses a number of open source projects to work properly:

* [AngularJS] - HTML enhanced for web apps!
* [Ace Editor] - awesome web-based text editor
* [markdown-it] - Markdown parser done right. Fast and easy to extend.
* [Twitter Bootstrap] - great UI boilerplate for modern web apps
* [node.js] - evented I/O for the backend
* [Express] - fast node.js network app framework [@tjholowaychuk]
* [Gulp] - the streaming build system
* [Breakdance](http://breakdance.io) - HTML to Markdown converter
* [jQuery] - duh

And of course Dillinger itself is open source with a [public repository][dill]
 on GitHub.

### Installation

Dillinger requires [Node.js](https://nodejs.org/) v4+ to run.

Install the dependencies and devDependencies and start the server.

```sh
$ cd dillinger
$ npm install -d
$ node app
```

For production environments...

```sh
$ npm install --production
$ NODE_ENV=production node app
```

### Plugins

Dillinger is currently extended with the following plugins. Instructions on how to use them in your own application are linked below.

| Plugin | README |
| ------ | ------ |
| Dropbox | [plugins/dropbox/README.md][PlDb] |
| Github | [plugins/github/README.md][PlGh] |
| Google Drive | [plugins/googledrive/README.md][PlGd] |
| OneDrive | [plugins/onedrive/README.md][PlOd] |
| Medium | [plugins/medium/README.md][PlMe] |
| Google Analytics | [plugins/googleanalytics/README.md][PlGa] |


### Development

Want to contribute? Great!

Dillinger uses Gulp + Webpack for fast developing.
Make a change in your file and instantanously see your updates!

Open your favorite Terminal and run these commands.

First Tab:
```sh
$ node app
```

Second Tab:
```sh
$ gulp watch
```

(optional) Third:
```sh
$ karma test
```
#### Building for source
For production release:
```sh
$ gulp build --prod
```
Generating pre-built zip archives for distribution:
```sh
$ gulp build dist --prod
```
### Docker
Dillinger is very easy to install and deploy in a Docker container.

By default, the Docker will expose port 8080, so change this within the Dockerfile if necessary. When ready, simply use the Dockerfile to build the image.

```sh
cd dillinger
docker build -t joemccann/dillinger:${package.json.version} .
```
This will create the dillinger image and pull in the necessary dependencies. Be sure to swap out `${package.json.version}` with the actual version of Dillinger.

Once done, run the Docker image and map the port to whatever you wish on your host. In this example, we simply map port 8000 of the host to port 8080 of the Docker (or whatever port was exposed in the Dockerfile):

```sh
docker run -d -p 8000:8080 --restart="always" <youruser>/dillinger:${package.json.version}
```

Verify the deployment by navigating to your server address in your preferred browser.

```sh
127.0.0.1:8000
```

#### Kubernetes + Google Cloud

See [KUBERNETES.md](https://github.com/joemccann/dillinger/blob/master/KUBERNETES.md)


### Todos

 - Write MORE Tests
 - Add Night Mode

License
----

MIT


**Free Software, Hell Yeah!**

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)


   [dill]: <https://github.com/joemccann/dillinger>
   [git-repo-url]: <https://github.com/joemccann/dillinger.git>
   [john gruber]: <http://daringfireball.net>
   [df1]: <http://daringfireball.net/projects/markdown/>
   [markdown-it]: <https://github.com/markdown-it/markdown-it>
   [Ace Editor]: <http://ace.ajax.org>
   [node.js]: <http://nodejs.org>
   [Twitter Bootstrap]: <http://twitter.github.com/bootstrap/>
   [jQuery]: <http://jquery.com>
   [@tjholowaychuk]: <http://twitter.com/tjholowaychuk>
   [express]: <http://expressjs.com>
   [AngularJS]: <http://angularjs.org>
   [Gulp]: <http://gulpjs.com>

   [PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
   [PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
   [PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
   [PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
   [PlMe]: <https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md>
   [PlGa]: <https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md>
