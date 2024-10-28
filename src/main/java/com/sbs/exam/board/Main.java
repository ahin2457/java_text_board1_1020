package com.sbs.exam.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Todo List
- [o] /usr/article/detail 명령어 처리
- [] /urs/article/write 입력시 가장 최신 게시물 노출

 */
public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    // while문 바깥의 변수는 while문 입장에서 전역변수
    int lastArticleLastId = 0;
    Article lastArticle = null;

    // 여러게의 게시물 담기
    // ArrayList<Article> articles = new ArrayList<Article>();
    // 뒤에 Array가 있으면 앞에 Array 생략 가능
    // 앞에 Article이 있으면 뒤에 Article 생략 가능
    List<Article> articles = new ArrayList<>();

    // 테스트 게시물 작성 시작
    articles.add(new Article(1,"제목1","내용1"));
    articles.add(new Article(2,"제목2","내용2"));
    articles.add(new Article(3,"제목3","내용3"));
    // 테스트 게시물 작성 끝

    System.out.println("== 텍스트 게시판 v 0.1 ==");
    System.out.println("== 게시판을 시작합니다. ==");

    while (true){
      System.out.printf("명령어) ");
      String cmd = sc.nextLine();



      if(cmd.equals("/usr/article/write") ){
        System.out.println("== 게시물 상세보기 ==");
        System.out.print("제목 : ");
        String subject =  sc.nextLine();

        System.out.print("내용 : ");
        String content = sc.nextLine();

        /*
        int id = articleLastId + 1;
        articleLastId = id; // articleLastId는 초기값 0이기 떄문에 id 값으로 갱신시켜줌
         */
        // 위 코드를 한줄로 줄이기

        // 전이증감 연산?
        //  선 증가시킨 값을 변수에다가 집어 넣은 다음에 그 값을 사용
        int id = ++lastArticleLastId;

        // Article 객체의 주소가 출력
        Article article = new Article(id ,subject ,content);
        // 입력 받고난 게시물의 객체 주소를 lastArticle에 넣어줌
        lastArticle = article;

        // article(주소값)을 articles(list저장 객체)에 넣어준다.
        articles.add(article);

        //article.id = id;
        //article.subject = subject;
        //article.content = content;

        System.out.println("생성된 게시물 객체 : " + article);
        System.out.printf("%d번 게시물이 등록되었습니다.\n", article.id);
      }
      else if(cmd.equals("/usr/article/list") ) {
        if (articles.isEmpty()) {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        System.out.println("== 게시물 리스트 ==");

        /*
          for(int i = 0; i < articles.size(); i++){
            Article article = articles.get(i);
            System.out.printf("%d | %s\n ", article.id, article.subject);
          }
        }
        */
         /*
        for(Article article : articles){ // articles에는 객체값이 아닌 주소가 들어가 있다
          System.out.printf("   %d | %s\n",article.id,article.subject );
        }
         */
        /*
         자바 Stream ?
         java8부터 추가된 기술로 람다를 활용해 배열과 컬렉션을 함수형으로 간단하게 처리할 수 있는 기술

         기존의 for문과 iterator를 사용하면 코드가 길어져서 가독성과 재사용성이 떨어지며 데이터 타입마다 다른 방식으로 다뤄야 하는 불편함이 있다.
         스트림은 데이터 소스를 추상화하고,데이터를 다루는데 자주 사용되는 메소드를 정의해 놓아서 데이터 소스에 상관없이 모두 같은 방식으로 다룰 수 있으므로 코드의 재사용성이 높아진다.

         특징
         - 원본 데이터 소스를 변경하지 않는다. 읽기만 한다.
         - 일회용이다 한번 사용하면 닫혀서 재사용이 불가능하다.
         - 최종 연산 전까지 중간 연산을 수행하지 않는다.
         - 작업을 내부 반복으로 처리한다:forEach() 매개변수에 대입된 람다식을 데이터 소스의 모든 요소에 적용한다.
         - 병렬 처리가 쉽다: 멀티쓰레드 사용
         - 기본 스트림을 제공한다:Stream<Ineger> 대신 IntStream이 제공되어서 오토박싱과 언박싱 등의 불필요한 과정이 생략되고 숫자의 경우 유용한 메소드를 추가로 제공한다.
         */
        articles.stream()
            .forEach(article -> System.out.printf(" %d | %s\n ", article.id, article.subject)
            );
      }
      else if(cmd.equals("/usr/article/detail") ){
        Article article = lastArticle; // 주소값의 연결

        if(article == null){
          System.out.println("게시물이 존재하지 않습니다.");
          continue; //밑에 코드 스킵
        }

        System.out.println("== 게시물 상세보기 ==");
        System.out.printf("번호 : %d\n", article.id);
        System.out.printf("제목 : %d\n", article.subject);
        System.out.printf("내용 : %d\n", article.content);
        //break; // 반복문 빠져나오기
      }
      else if(cmd.equals("exit")){
        System.out.println("명령어를 입력해주세요.");
      }
      else{
        System.out.println("올바른 명령어가 아닙니다.");
      }

      System.out.printf("입력받은 명령어: %s\n",cmd);

    }


    //sc.close();

  }
}


// 데이터가 저장이 안되고 날라가기 때문에 class를 통해서 객체를 통해서 관리하기
// 모든 클래스는 obejct 클래스를 상속받는다.
class Article {
  int id;
  String subject;
  String content;

  Article(int id, String subject, String content){
    this.id = id;
    this.subject = subject;
    this.content = content;
  }
  // 데이터 확인
  @Override  // 어노테이션
  public String toString(){ // 매서드 오버라이딩 - 부모가 정의해 놓은걸 자식이 물려받을 수 있지만 자식선에서 출력상태가 마음에 안들어 재정의하는것
    return "{id : %d, subject : \"%s\", content : \"%s\"}".formatted(id,subject,content);
  }
}
