package com.sbs.exam.board;

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

    System.out.println("== 텍스트 게시판 v 0.1 ==");

    while (true){
      System.out.printf("명령어 ");
      String cmd = sc.nextLine();



      if(cmd.equals("/urs/article/write") ){
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
        Article article = new Article(id,subject,content);
        // 입력 받고난 게시물의 객체 주소를 lastArticle에 넣어줌
        lastArticle = article;

        //article.id = id;
        //article.subject = subject;
        //article.content = content;

        System.out.println("생성된 게시물 객체 : " + article);
        System.out.printf("%d번 게시물이 등록되었습니다.\n", article.id);
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
