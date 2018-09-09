package practice;

import java.util.*;
import java.util.Vector;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class Term_Project {

   String manager;
   String managerId;
   String staffName;
   String staffId;
   String position;
   boolean devel;
   static int managerCount = 0;// 만들어진 매니저수
   static int staffCount = 0;// 만들어진 직원수
   static Date Time;			//시간 저장 함수
   static Vector<String> department = new Vector<String>();
   HashMap<String, Team> hashteam = new HashMap<String, Team>();

   public static void main(String[] arg) {
      Scanner sc = new Scanner(System.in);
      Term_Project company = new Term_Project();		//객체생성

      while (true) {
         System.out.println("********************");
         System.out.println("회사관리 프로그램");
         System.out.println("0. 회사직원 보여주기");
         System.out.println("1. 부서생성");
         System.out.println("2. 신규직원추가");
         System.out.println("3. 직원검색");
         System.out.println("4. 직원수정 및 삭제");
         System.out.println("5. 히스토리");
         System.out.println("6. 종료");
         System.out.println("********************");
         System.out.print(">>");
         int choice = sc.nextInt();
         Calendar now = Calendar.getInstance();
         switch (choice) {
         case 0:
            company.print();		//보여주기
            if (department.isEmpty()) {//부서가 비었다면 먼저생성 메시지 날림 벡터에있는 isEmpty사용하려고 하려고 했더니 불가
               System.out.println("부서를 먼저 생성하세요.");
               continue;
            }
            break;
         case 1:
            company.MakeDepartment();		//부서 생성
            Time = now.getTime();		//현재 시간 저장
            company.print();
            break;
         case 2:
            if (department.isEmpty()) {//부서가 비었다면 부서먼저
               System.out.println("부서를 먼저 생성하세요.");
               continue;
            }
            company.plusEmployee();
            Time = now.getTime();
            company.print();
            break;
         case 3:
            if (department.isEmpty()) {//부서가 비었다면 부서 먼저
               System.out.println("부서를 먼저 생성하세요.");
               continue;
            }
            company.search();
            break;
         case 4:
            if (department.isEmpty()) {//부서가 비었다면 부서 먼저
               System.out.println("부서를 먼저 생성하세요.");
               continue;
            }
            company.ModifyStaff();		//파일 수정 및 삭제
            break;
         case 5:
            company.history(); // 파일생성
            break;
         case 6:
            System.out.println("종료합니다.");
            break;
         default:
            System.out.println("잘못된 번호 입력 입니다. 다시 입력 하세요.");
            break;
         }
         if (choice == 6)		//6번 선택, 무한 루프에서 벗어나기
            break;
      }

   }
   
   // 부서와 직원 직급을 보여주는 매소드
         public void print() {

            Set<String> keys = hashteam.keySet();
            Iterator<String> it = keys.iterator();

            while (it.hasNext()) {
               String department = it.next();//키 값이 다 나올 때 까지 계속 보여줌
               Team team = hashteam.get(department);//이 밑에는 부서를 택했을 때 보여줄 수 있는 것들

               System.out.println("<" + department + "> ");
               System.out.println("매니저:" + team.manager + " ID:" + team.managerId);
               for (int i = 0; i < team.staff.size(); i++) {
                  System.out.print("이름:" + team.staff.get(i) + " ID:" + team.id.get(i) + " 역할:" + team.position.get(i));
                  System.out.println();
               }
            }
         }


   // 부서 만드는 매소드
   public void MakeDepartment() {
      
      Scanner sc = new Scanner(System.in);
      System.out.println("만들 부서 종류를 선택하세요. 부서생성에는 매니저와 직원1명만 입력합니다.");
      System.out.println("1. 개발팀");
      System.out.println("2. 개발지원팀");
      System.out.print(">>");
      int choice = sc.nextInt();
      while (true) {
         boolean same=false;
         System.out.print("부서 이름을 입력하세요.>>");
         department.add(sc.next());
         // 부서생성할때 같은 부서이름이 있는지 확인
         if (department.size() > 1) {//만약 부서가 1개 이상이라면 실행
            for (int i = 0; i < department.size(); i++) {
               if (department.get(department.size() - 1).equals(department.get(i))) {
                  System.out.println("같은 부서이름이 있습니다. 다시입력하세요.");
                  department.remove(department.size() - 1);
                  break;
               } 
               else  {
                  same = true;// 같은 부서 이름이 없다는 표시
                  break;
               }
            }
         }
         else same=true;//부서가 없을 때 생성시에는 true넣음
         if (same)
            break;
      }
      System.out.print("매니저 이름을 입력하세요.>>");
      manager = sc.next();
      managerCount++;		//회사 매니저 수 증가
      managerId = "m_" + managerCount;// 매니저 아이디는 중복되지 않게 자동으로 만들어서 대입
      System.out.print("직원 이름을 입력하세요.>>");
      staffName = sc.next();
      staffCount++;	//회사 직원 수 증가
      staffId = "s_" + staffCount;// 직원 아이디는 중복되지 않게 자동으로 만들어서 대입

      if (choice == 1) {//만약 개발팀을 만들기로 선택했다면
         devel=true;
         while (true) {
            System.out.println("직원 역할을 선택하세요.");
            System.out.println("1.SW Developer");
            System.out.println("2.SW Tester");
            System.out.print(">>");
            int choice2 = sc.nextInt();
            switch (choice2) {
            case 1:
               position = "SW Developer";
               break;
            case 2:
               position = "SW Tester";
               break;
            default:
               System.out.println("잘못된 번호 입력 입니다. 다시 입력 하세요");
               continue;
            }
            break;	//switch문 나오면 바로 무한루프 탈출
         }
      } else {	//개발팀이 아니라면
         position = "staff";
         devel = false;
      }
      //해쉬맵 생성
      hashteam.put(department.get(department.size() - 1), new Team(manager, managerId, staffName, staffId, position,devel));
   }

   // 신규직원추가를 위한 매소드
   public void plusEmployee() {
      Scanner sc = new Scanner(System.in);
      String departmentName;
      boolean check=false;//이름확인을 위한 변수

      while (true) {
         System.out.print("부서 이름을 입력하세요.>>");
         departmentName = sc.next();
         

         for (int i = 0; i < department.size(); i++) {//신규 직원을 추가하는데 입력 받은 부서명이 없는 경우
            if (department.get(i).equals(departmentName)) {
               departmentName = department.get(i);
               check=true;
               break;
            }
         }
         if (check == true) break;//일치하는 부서이름이 있다면 무한루프 탈출
         else{
            System.out.println("잘못된 부서이름입니다. 다시입력하세요.");
            continue;
         }            
      }
      
      Team team = hashteam.get(departmentName);
      System.out.print("직원 이름을 입력하세요.>>");
      staffName = sc.next();
      staffCount++;
      staffId = "s_" + staffCount;
      if (team.devel) {//추가 할 부서가 개발팀이라면
         while (true) {
            System.out.println("직원 직급을 선택하세요.");
            System.out.println("1.SW Developer");
            System.out.println("2.SW Tester");
            System.out.print(">>");
            int choice2 = sc.nextInt();
            switch (choice2) {
            case 1:
               position = "SW Developer";
               break;
            case 2:
               position = "SW Tester";
               break;
            default:
               System.out.println("잘못된 번호 입력 입니다. 다시 입력 하세요");
               continue;
            }
            if(choice2==1||choice2==2)
            break;
         }
      } else		//개발팀이 아니라면
         position = "staff";

      team.staff.add(staffName);		//staff벡터에 staffName을 추가하라
      team.id.add(staffId);			//id벡터에 staffId를 추가하라
      team.position.add(position);		//position벡터에 position을 추가하라
   }
   


   //직원검색 매소드
   public void search() {
      Scanner sc = new Scanner(System.in);
      int num=0;
      boolean check=false;//check는 없는 단어라는 표시를 위한 것 일치하는 단어가 있다면 true 
      String s_name, s_department, s_manager;
      
      

      while(true) {
      System.out.println("번호를 선택하세요.");
      System.out.println("1. 이름 검색");
      System.out.println("2. 부서명 검색");
      System.out.println("3. Manager 검색");
      System.out.print(">>");
      num=sc.nextInt();
      
      switch(num) {
      
         case 1:// 이름검색
            while (true) {//틀린 이름을 입력했을 시 다시 입력을 위한 무한루프

               System.out.print("이름을 입력하세요.>>");
               s_name = sc.next();
               
               Set<String> keys = hashteam.keySet();
               Iterator<String> it = keys.iterator();

               while (it.hasNext()) {
                  String department = it.next();
                  Team team = hashteam.get(department);

                  if (team.manager.equals(s_name)) {// 일치하는 매니저 이름이 있다면 check에 true대입
                     System.out.println("<" + department + "> 매니저:" + s_name + " ID:" + team.managerId);
                     check=true;
                  }

                  for (int i = 0; i < team.staff.size(); i++) {
                     if (team.staff.get(i).equals(s_name)) {// 같은 직원이름 있다면  check에 true대입
                        System.out.println("<" + department + "> 이름:" + team.staff.get(i) + " ID:"
                              + team.id.get(i) + " 역할:" + team.position.get(i));
                        check=true;
                     }
                  }
               }
               if (check == true)
                  break;
               else {// 일치하는 이름이 없다면 다시 입력
                  System.out.println("잘못된 이름입니다. 다시입력하세요. ");
                  continue;
               }
            }
            break;
         case 2:// 부서명검색
            while (true) {

               System.out.print("부서명을 입력하세요.>>");
               s_department = sc.next();

               for (int i = 0; i < department.size(); i++) {
                  if (department.get(i).equals(s_department)) {//일치하는 부서이름이 있다면 check에 true대입
                     s_department = department.get(i);
                     check=true;
                     break;
                  }
               }
               
               if (check == true)//일치하는 부서이름이 있다면 무한루프 탈출
                  break;
               else {// 일치하는 이름이 없다면 다시 입력
                  System.out.println("잘못된 부서이름입니다. 다시입력하세요. ");
                  continue;
               }
            }
         
         Team team = hashteam.get(s_department);//이 밑에는 부서를 택했을 때 보여줄 수 있는 것들
         System.out.println("<" +s_department + "> ");
         System.out.println("매니저:" + team.manager + " ID:" + team.managerId);
         for (int i = 0; i < team.staff.size(); i++) {
            System.out.print("이름:" + team.staff.get(i) + " ID:" + team.id.get(i) + " 역할:" + team.position.get(i));
            System.out.println();
         }   
         break;
      case 3://매니저 이름 검색      
            while (true) {
               
               System.out.print("manager 이름을 입력하세요.>>");
               s_manager=sc.next();
               
               Set<String> keys = hashteam.keySet();
               Iterator<String> it = keys.iterator();

               while (it.hasNext()) {
                  String department = it.next();
                  Team team2 = hashteam.get(department);		//앞의 함수에서 객체의 형성이 끝나므로
                  if (team2.manager.equals(s_manager)) {// 일치하는 매니저 이름이 있다면 check에 true대입
                     check=true;
                     System.out.println("<" + department + "> ");
                     System.out.println("매니저:" + s_manager + " ID:" + team2.managerId);
                     for (int i = 0; i < team2.staff.size(); i++) {
                        System.out.print("이름:" + team2.staff.get(i) + " ID:" + team2.id.get(i) + " 역할:"
                              + team2.position.get(i));
                        System.out.println();
                     }
                  }
               }
               if (check == true)//일치하는 manager이름이 있다면 무한루프 탈출
                  break;
               else {// 일치하는 이름이 없다면 다시 입력
                  System.out.println("잘못된 manager 이름입니다. 다시입력하세요. ");
                  continue;
               }
            }
            break;
      default:
         System.out.println("잘못된 번호입니다. 다시입력하세요.");
         continue;
         
      }
         if(num>=1 && num<=3)//선택한 번호가 1~3사이라면 무한루프 빠져나옴
            break;
      }
   }

   //수정삭제 매소드
   public void ModifyStaff(){
	      Team team = new Team(manager, managerId, staffName, staffId, position, devel);
	         Scanner sc = new Scanner(System.in);
	         int number, index;         //index는 저장된 번호 저장
	         String ModiName, ModiId, Modifyname;         // 수정될 이름, 수정될 아이디, 수정된 이름

	          Set<String> keys = hashteam.keySet();
	          Iterator<String> it = keys.iterator();
	         System.out.println("수정하려면 1번, 삭제하려면 2번을 입력하세요.");
	         number = sc.nextInt();
	         switch(number){
	         case 1:		//수정하려고 할 때
	             System.out.print("수정하고자 하는 직원의 이름을 입력하세요. >>");
	             ModiName = sc.next();

	             while (it.hasNext()) {
	                String department = it.next();// 키값이 다나올때까지 계속보여줌
	                Team team1 = hashteam.get(department);

	                if (team1.staff.contains(ModiName)) {
	                   System.out.print("수정하고자 하는 직원 아이디를 입력하세요. >>");
	                   ModiId = sc.next();

	                   if (team1.id.contains(ModiId)) { // id에 수정될 아이디가 있다면
	                      index = team1.id.indexOf(ModiId);
	                      System.out.print("수정할 이름을 입력해주세요. >>");
	                      Modifyname = sc.next();
	                      team1.staff.remove(index);  //index번째 삭제
	                      team1.staff.add(index, Modifyname); // index 번호에 Modifyname을 저장
	                   } 
	                }
	             }
	               break;
	         case 2:		//삭제하고자 할 때
	           System.out.println("삭제하고자 하는 직원의 이름을 입력하세요. >>");
	           ModiName = sc.next();

	           while(it.hasNext()){
	              String department = it.next();
	              Team team2 = hashteam.get(department);

	              if(team2.staff.contains(ModiName)){
	                 System.out.print("삭제하고자 하는 직원의 아이디를 입력하세요(매니저가 아닌 직원만 가능합니다.)>>");
	                 ModiId = sc.next();

	                 if(team2.id.contains(ModiId)){
	                    index = team2.id.indexOf(ModiId);
	                    team2.staff.remove(index);   //staff의 index번째를 삭제
	                    team2.id.remove(index);      //id의 index번째를 삭제
	                 }
	              }
	           }
	         }
	      }




   // 기록담당 매소드
   public void history() {
      try {
         FileWriter fw = new FileWriter("out.txt");	    //패키지파일 있는 곳 저장됨
         BufferedWriter dw = new BufferedWriter(fw);
         dw.flush();

      } catch (Exception e) {
e.printStackTrace();
      }
   }
}
   

class Team {
   boolean devel;//개발팀이면 true 아니면 false
   String manager;
   String managerId;
   Vector<String> staff = new Vector<String>();
   Vector<String> id = new Vector<String>();
   Vector<String> position = new Vector<String>();

   public Team(String manager, String managerId, String staffname, String id, String position, boolean devel) {
      this.manager = manager;
      this.managerId = managerId;
      this.staff.add(staffname);
      this.id.add(id);
      this.position.add(position);
      this.devel=devel;
   }
}
 
