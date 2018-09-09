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
   static int managerCount = 0;// ������� �Ŵ�����
   static int staffCount = 0;// ������� ������
   static Date Time;			//�ð� ���� �Լ�
   static Vector<String> department = new Vector<String>();
   HashMap<String, Team> hashteam = new HashMap<String, Team>();

   public static void main(String[] arg) {
      Scanner sc = new Scanner(System.in);
      Term_Project company = new Term_Project();		//��ü����

      while (true) {
         System.out.println("********************");
         System.out.println("ȸ����� ���α׷�");
         System.out.println("0. ȸ������ �����ֱ�");
         System.out.println("1. �μ�����");
         System.out.println("2. �ű������߰�");
         System.out.println("3. �����˻�");
         System.out.println("4. �������� �� ����");
         System.out.println("5. �����丮");
         System.out.println("6. ����");
         System.out.println("********************");
         System.out.print(">>");
         int choice = sc.nextInt();
         Calendar now = Calendar.getInstance();
         switch (choice) {
         case 0:
            company.print();		//�����ֱ�
            if (department.isEmpty()) {//�μ��� ����ٸ� �������� �޽��� ���� ���Ϳ��ִ� isEmpty����Ϸ��� �Ϸ��� �ߴ��� �Ұ�
               System.out.println("�μ��� ���� �����ϼ���.");
               continue;
            }
            break;
         case 1:
            company.MakeDepartment();		//�μ� ����
            Time = now.getTime();		//���� �ð� ����
            company.print();
            break;
         case 2:
            if (department.isEmpty()) {//�μ��� ����ٸ� �μ�����
               System.out.println("�μ��� ���� �����ϼ���.");
               continue;
            }
            company.plusEmployee();
            Time = now.getTime();
            company.print();
            break;
         case 3:
            if (department.isEmpty()) {//�μ��� ����ٸ� �μ� ����
               System.out.println("�μ��� ���� �����ϼ���.");
               continue;
            }
            company.search();
            break;
         case 4:
            if (department.isEmpty()) {//�μ��� ����ٸ� �μ� ����
               System.out.println("�μ��� ���� �����ϼ���.");
               continue;
            }
            company.ModifyStaff();		//���� ���� �� ����
            break;
         case 5:
            company.history(); // ���ϻ���
            break;
         case 6:
            System.out.println("�����մϴ�.");
            break;
         default:
            System.out.println("�߸��� ��ȣ �Է� �Դϴ�. �ٽ� �Է� �ϼ���.");
            break;
         }
         if (choice == 6)		//6�� ����, ���� �������� �����
            break;
      }

   }
   
   // �μ��� ���� ������ �����ִ� �żҵ�
         public void print() {

            Set<String> keys = hashteam.keySet();
            Iterator<String> it = keys.iterator();

            while (it.hasNext()) {
               String department = it.next();//Ű ���� �� ���� �� ���� ��� ������
               Team team = hashteam.get(department);//�� �ؿ��� �μ��� ������ �� ������ �� �ִ� �͵�

               System.out.println("<" + department + "> ");
               System.out.println("�Ŵ���:" + team.manager + " ID:" + team.managerId);
               for (int i = 0; i < team.staff.size(); i++) {
                  System.out.print("�̸�:" + team.staff.get(i) + " ID:" + team.id.get(i) + " ����:" + team.position.get(i));
                  System.out.println();
               }
            }
         }


   // �μ� ����� �żҵ�
   public void MakeDepartment() {
      
      Scanner sc = new Scanner(System.in);
      System.out.println("���� �μ� ������ �����ϼ���. �μ��������� �Ŵ����� ����1�� �Է��մϴ�.");
      System.out.println("1. ������");
      System.out.println("2. ����������");
      System.out.print(">>");
      int choice = sc.nextInt();
      while (true) {
         boolean same=false;
         System.out.print("�μ� �̸��� �Է��ϼ���.>>");
         department.add(sc.next());
         // �μ������Ҷ� ���� �μ��̸��� �ִ��� Ȯ��
         if (department.size() > 1) {//���� �μ��� 1�� �̻��̶�� ����
            for (int i = 0; i < department.size(); i++) {
               if (department.get(department.size() - 1).equals(department.get(i))) {
                  System.out.println("���� �μ��̸��� �ֽ��ϴ�. �ٽ��Է��ϼ���.");
                  department.remove(department.size() - 1);
                  break;
               } 
               else  {
                  same = true;// ���� �μ� �̸��� ���ٴ� ǥ��
                  break;
               }
            }
         }
         else same=true;//�μ��� ���� �� �����ÿ��� true����
         if (same)
            break;
      }
      System.out.print("�Ŵ��� �̸��� �Է��ϼ���.>>");
      manager = sc.next();
      managerCount++;		//ȸ�� �Ŵ��� �� ����
      managerId = "m_" + managerCount;// �Ŵ��� ���̵�� �ߺ����� �ʰ� �ڵ����� ���� ����
      System.out.print("���� �̸��� �Է��ϼ���.>>");
      staffName = sc.next();
      staffCount++;	//ȸ�� ���� �� ����
      staffId = "s_" + staffCount;// ���� ���̵�� �ߺ����� �ʰ� �ڵ����� ���� ����

      if (choice == 1) {//���� �������� ������ �����ߴٸ�
         devel=true;
         while (true) {
            System.out.println("���� ������ �����ϼ���.");
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
               System.out.println("�߸��� ��ȣ �Է� �Դϴ�. �ٽ� �Է� �ϼ���");
               continue;
            }
            break;	//switch�� ������ �ٷ� ���ѷ��� Ż��
         }
      } else {	//�������� �ƴ϶��
         position = "staff";
         devel = false;
      }
      //�ؽ��� ����
      hashteam.put(department.get(department.size() - 1), new Team(manager, managerId, staffName, staffId, position,devel));
   }

   // �ű������߰��� ���� �żҵ�
   public void plusEmployee() {
      Scanner sc = new Scanner(System.in);
      String departmentName;
      boolean check=false;//�̸�Ȯ���� ���� ����

      while (true) {
         System.out.print("�μ� �̸��� �Է��ϼ���.>>");
         departmentName = sc.next();
         

         for (int i = 0; i < department.size(); i++) {//�ű� ������ �߰��ϴµ� �Է� ���� �μ����� ���� ���
            if (department.get(i).equals(departmentName)) {
               departmentName = department.get(i);
               check=true;
               break;
            }
         }
         if (check == true) break;//��ġ�ϴ� �μ��̸��� �ִٸ� ���ѷ��� Ż��
         else{
            System.out.println("�߸��� �μ��̸��Դϴ�. �ٽ��Է��ϼ���.");
            continue;
         }            
      }
      
      Team team = hashteam.get(departmentName);
      System.out.print("���� �̸��� �Է��ϼ���.>>");
      staffName = sc.next();
      staffCount++;
      staffId = "s_" + staffCount;
      if (team.devel) {//�߰� �� �μ��� �������̶��
         while (true) {
            System.out.println("���� ������ �����ϼ���.");
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
               System.out.println("�߸��� ��ȣ �Է� �Դϴ�. �ٽ� �Է� �ϼ���");
               continue;
            }
            if(choice2==1||choice2==2)
            break;
         }
      } else		//�������� �ƴ϶��
         position = "staff";

      team.staff.add(staffName);		//staff���Ϳ� staffName�� �߰��϶�
      team.id.add(staffId);			//id���Ϳ� staffId�� �߰��϶�
      team.position.add(position);		//position���Ϳ� position�� �߰��϶�
   }
   


   //�����˻� �żҵ�
   public void search() {
      Scanner sc = new Scanner(System.in);
      int num=0;
      boolean check=false;//check�� ���� �ܾ��� ǥ�ø� ���� �� ��ġ�ϴ� �ܾ �ִٸ� true 
      String s_name, s_department, s_manager;
      
      

      while(true) {
      System.out.println("��ȣ�� �����ϼ���.");
      System.out.println("1. �̸� �˻�");
      System.out.println("2. �μ��� �˻�");
      System.out.println("3. Manager �˻�");
      System.out.print(">>");
      num=sc.nextInt();
      
      switch(num) {
      
         case 1:// �̸��˻�
            while (true) {//Ʋ�� �̸��� �Է����� �� �ٽ� �Է��� ���� ���ѷ���

               System.out.print("�̸��� �Է��ϼ���.>>");
               s_name = sc.next();
               
               Set<String> keys = hashteam.keySet();
               Iterator<String> it = keys.iterator();

               while (it.hasNext()) {
                  String department = it.next();
                  Team team = hashteam.get(department);

                  if (team.manager.equals(s_name)) {// ��ġ�ϴ� �Ŵ��� �̸��� �ִٸ� check�� true����
                     System.out.println("<" + department + "> �Ŵ���:" + s_name + " ID:" + team.managerId);
                     check=true;
                  }

                  for (int i = 0; i < team.staff.size(); i++) {
                     if (team.staff.get(i).equals(s_name)) {// ���� �����̸� �ִٸ�  check�� true����
                        System.out.println("<" + department + "> �̸�:" + team.staff.get(i) + " ID:"
                              + team.id.get(i) + " ����:" + team.position.get(i));
                        check=true;
                     }
                  }
               }
               if (check == true)
                  break;
               else {// ��ġ�ϴ� �̸��� ���ٸ� �ٽ� �Է�
                  System.out.println("�߸��� �̸��Դϴ�. �ٽ��Է��ϼ���. ");
                  continue;
               }
            }
            break;
         case 2:// �μ���˻�
            while (true) {

               System.out.print("�μ����� �Է��ϼ���.>>");
               s_department = sc.next();

               for (int i = 0; i < department.size(); i++) {
                  if (department.get(i).equals(s_department)) {//��ġ�ϴ� �μ��̸��� �ִٸ� check�� true����
                     s_department = department.get(i);
                     check=true;
                     break;
                  }
               }
               
               if (check == true)//��ġ�ϴ� �μ��̸��� �ִٸ� ���ѷ��� Ż��
                  break;
               else {// ��ġ�ϴ� �̸��� ���ٸ� �ٽ� �Է�
                  System.out.println("�߸��� �μ��̸��Դϴ�. �ٽ��Է��ϼ���. ");
                  continue;
               }
            }
         
         Team team = hashteam.get(s_department);//�� �ؿ��� �μ��� ������ �� ������ �� �ִ� �͵�
         System.out.println("<" +s_department + "> ");
         System.out.println("�Ŵ���:" + team.manager + " ID:" + team.managerId);
         for (int i = 0; i < team.staff.size(); i++) {
            System.out.print("�̸�:" + team.staff.get(i) + " ID:" + team.id.get(i) + " ����:" + team.position.get(i));
            System.out.println();
         }   
         break;
      case 3://�Ŵ��� �̸� �˻�      
            while (true) {
               
               System.out.print("manager �̸��� �Է��ϼ���.>>");
               s_manager=sc.next();
               
               Set<String> keys = hashteam.keySet();
               Iterator<String> it = keys.iterator();

               while (it.hasNext()) {
                  String department = it.next();
                  Team team2 = hashteam.get(department);		//���� �Լ����� ��ü�� ������ �����Ƿ�
                  if (team2.manager.equals(s_manager)) {// ��ġ�ϴ� �Ŵ��� �̸��� �ִٸ� check�� true����
                     check=true;
                     System.out.println("<" + department + "> ");
                     System.out.println("�Ŵ���:" + s_manager + " ID:" + team2.managerId);
                     for (int i = 0; i < team2.staff.size(); i++) {
                        System.out.print("�̸�:" + team2.staff.get(i) + " ID:" + team2.id.get(i) + " ����:"
                              + team2.position.get(i));
                        System.out.println();
                     }
                  }
               }
               if (check == true)//��ġ�ϴ� manager�̸��� �ִٸ� ���ѷ��� Ż��
                  break;
               else {// ��ġ�ϴ� �̸��� ���ٸ� �ٽ� �Է�
                  System.out.println("�߸��� manager �̸��Դϴ�. �ٽ��Է��ϼ���. ");
                  continue;
               }
            }
            break;
      default:
         System.out.println("�߸��� ��ȣ�Դϴ�. �ٽ��Է��ϼ���.");
         continue;
         
      }
         if(num>=1 && num<=3)//������ ��ȣ�� 1~3���̶�� ���ѷ��� ��������
            break;
      }
   }

   //�������� �żҵ�
   public void ModifyStaff(){
	      Team team = new Team(manager, managerId, staffName, staffId, position, devel);
	         Scanner sc = new Scanner(System.in);
	         int number, index;         //index�� ����� ��ȣ ����
	         String ModiName, ModiId, Modifyname;         // ������ �̸�, ������ ���̵�, ������ �̸�

	          Set<String> keys = hashteam.keySet();
	          Iterator<String> it = keys.iterator();
	         System.out.println("�����Ϸ��� 1��, �����Ϸ��� 2���� �Է��ϼ���.");
	         number = sc.nextInt();
	         switch(number){
	         case 1:		//�����Ϸ��� �� ��
	             System.out.print("�����ϰ��� �ϴ� ������ �̸��� �Է��ϼ���. >>");
	             ModiName = sc.next();

	             while (it.hasNext()) {
	                String department = it.next();// Ű���� �ٳ��ö����� ��Ӻ�����
	                Team team1 = hashteam.get(department);

	                if (team1.staff.contains(ModiName)) {
	                   System.out.print("�����ϰ��� �ϴ� ���� ���̵� �Է��ϼ���. >>");
	                   ModiId = sc.next();

	                   if (team1.id.contains(ModiId)) { // id�� ������ ���̵� �ִٸ�
	                      index = team1.id.indexOf(ModiId);
	                      System.out.print("������ �̸��� �Է����ּ���. >>");
	                      Modifyname = sc.next();
	                      team1.staff.remove(index);  //index��° ����
	                      team1.staff.add(index, Modifyname); // index ��ȣ�� Modifyname�� ����
	                   } 
	                }
	             }
	               break;
	         case 2:		//�����ϰ��� �� ��
	           System.out.println("�����ϰ��� �ϴ� ������ �̸��� �Է��ϼ���. >>");
	           ModiName = sc.next();

	           while(it.hasNext()){
	              String department = it.next();
	              Team team2 = hashteam.get(department);

	              if(team2.staff.contains(ModiName)){
	                 System.out.print("�����ϰ��� �ϴ� ������ ���̵� �Է��ϼ���(�Ŵ����� �ƴ� ������ �����մϴ�.)>>");
	                 ModiId = sc.next();

	                 if(team2.id.contains(ModiId)){
	                    index = team2.id.indexOf(ModiId);
	                    team2.staff.remove(index);   //staff�� index��°�� ����
	                    team2.id.remove(index);      //id�� index��°�� ����
	                 }
	              }
	           }
	         }
	      }




   // ��ϴ�� �żҵ�
   public void history() {
      try {
         FileWriter fw = new FileWriter("out.txt");	    //��Ű������ �ִ� �� �����
         BufferedWriter dw = new BufferedWriter(fw);
         dw.flush();

      } catch (Exception e) {
e.printStackTrace();
      }
   }
}
   

class Team {
   boolean devel;//�������̸� true �ƴϸ� false
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
 
