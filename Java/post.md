# Series Giải thuật cơ bản , phần 1: tìm hiểu về mảng động (vector trong C++ / Arraylist trong Java...)

He lô, kồn ni chi wa, xin chào các bạn!
Đây chỉ là bài viết đầu tiên của mình , mong các bạn ủng hộ , và góp ý cho mình nhé nhé.

Đây thực chất chỉ là note cá nhân của mình trong việc ôn tập, nâng cao kỹ năn g giải uyết vấn đề trong lập trình, và theo mình, giải thuật là một trong những  kiến thức  quan trọng  cần phải  nắm vững để có thể  sớm đạt được cảnh giới của một Software Engineer giỏi. 

Summary: 
Bài viết gồm 3 phần.
- Góc nhìn của bản thân về  tầm quan trọng của giải thuật trong việc giải quyết vấn đề .
- Những kiến thức cần tìm hiểu về cấu trúc dữ liệu và giải thuật căn bản. 
- Khái quát về mảng động, và ví dụ liên quan. 

# Tầm quan trọng của giải thuật trong việc giải quyết vấn đề.

 Theo mình có ba miền kiến thức quan trọng, mà mỗi chúng ta cần phải nắm vững, để có thể trở thành một Software Engineer (SE) giỏi:
 - Kiến thức căn bản: cấu trúc dữ liệu và giải thuật, lập trình hướng đối tượng, TCP/IP, HTTP, Process and Thread...
 - Kiến thức theo từng domain (Web Development, Mobile Development, Embedded System..) mà bạn muốn theo đuổi.
 - Kiến thức để phân tích, thiết kế hệ thống.

Trong bài viết này, mình xin đề cập kiến thức về cấu trúc dữ liệu và giải thuật nằm ở nội dung thứ nhất - kiến thức căn bản. 
 Hầu hết chúng ta đều đã học về cấu trúc dữ liệu và giải thuât cơ bản chúng ta ở trường đại học, tuy nhiên chúng ta hay quên chúng, vì thông thường, thời gian đầu khi đi làm, chúng ta tập trung vào việc sử dụng, tìm hiểu 
 về các kiến thức theo từng domain mà chúng ta theo đuổi nhiều hơn, vì đó là kiến thức chúng ta phải dùng hàng ngày. Sau một vài năm, kinh qua nhiều ngôn ngữ, thì chúng ta nhận ra 
 những kiến thức căn bản ở trên, là những kiến thức quan trọng, mà bất kỳ làm domain nào, cũng đều thấy bóng dáng của nó. 
 Giả sử như hệ thống bạn đang phát triển, cần lấy dữ liệu từ Database kèm theo nhiều điều kiện ràng buộc, sau đó hiển thị lên màn hình để cho người dùng thao tác, xử lý.
 Đây hẳn là một thao tác cơ bản trong nhiều hệ thống. Lúc này, chúng ta không chỉ cần có kiến thức về ngôn ngữ mà chúng ta đang sử dụng, mà chúng ta còn cần vận dụng các hiểu biết về cấu trúc dữ liệu và giải thuật, để xác định việc sử dụng cấu trúc dữ liệu nào để lưu trữ dữ liệu cho phù hợp với từng yêu cầu cụ thể về bộ nhớ, 
 thời gian thực thi...
 Vì vậy, nếu muốn nhanh chóng trở thành một SE giỏi, thì chúng ta cần nắm vững chúng.
 
# Những kiến thức cần tìm hiểu về cấu trúc dữ liệu và giải thuật căn bản.

Việc nắm vững kiến thức về cấu trúc dữ liệu và giải thuật căn bản là rất quan trọng trong việc giải quyết vấn đề.
Đối với mỗi ngôn ngữ, các cấu trúc dữ liệu có thể được implement khác nhau, nhưng đều có cùng ý nghĩa chung.
Dưới đây mình xin liệt kê một số kiến thức mà chúng ta cần tìm hiểu để master cấu trúc dữ liệu và giải thuật: 

1. Mảng động (ví dụ: vector trong C++, ArrayList trong Java...)
2. Các thuật toán về sorting, stack, queue.
3. Thuật toán về đồ thị, BFS, DFS, Dijkstra, Bellman-Ford, Floyd-Warshall
4. Cấu trúc dữ liệu và giải thuật liên quan tới Tree như Binary Search, Binary Search Tree.
5. Cấu trúc dữ liệu nâng cao, cấu trúc cây tiền tố. 

Bạn có thể học những kiến thức trên ở đâu ?
Có rất nhiều khoá học mà bạn có thể tìm kiếm. Mình xin phép liệt kê một số link dưới đây.
* [Breakdance](http://breakdance.io) - HTML to Markdown converter


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
