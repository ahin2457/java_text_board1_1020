public class Main {
  public static void main(String[] args) {
    Article article = new Article();
    article.id = 1;
    article.title = "제목";
    article.body = "내용";
  }
}

// extneds Object
// 모든 클래스는 기본적으로 Object 클래스를 상속받는다.
class Article extends  Object{
  int id;
  String title;
  String body;
}
