package Lesson_4;

public class Task3 {
    
      public static void startTask3() { 
          
          MFU mfu = new MFU(); 
          
          Thread t1 = new Thread(new Runnable() { 
              public void run() { 
                 
                      mfu.print(10); 
                  
              } 
          }); 
  
 
          Thread t2 = new Thread(new Runnable() { 
              public void run() { 
                 
                      mfu.print(10); 
                   
              } 
          }); 
  
 
          Thread t3 = new Thread(new Runnable() { 
              public void run() { 
                  
                      mfu.scan(10); 
                  
              } 
          }); 
          t1.start(); 
          t2.start(); 
          t3.start(); 
      } 
  } 
  
  class MFU { 
  
      static Object monPrint = new Object(); 
      static Object monScan = new Object(); 
 
      public void print(int countPages) { 
          synchronized (monPrint)  { 
          for (int i = 1; i <= countPages; i++) { 
              System.out.println("Отпечатана " + i + " страница"); 
              try { 
                  Thread.sleep(50); 
              } catch (InterruptedException e) { 
                  e.printStackTrace(); 
              } 
          } 
          System.out.println("Печать завершена"); 
          }
      } 
  
      public void scan(int countPages) { 
          synchronized (monScan)  { 
          for (int i = 1; i <= countPages; i++) { 
              System.out.println("Отсканирована " + i + " страница"); 
              try { 
                  Thread.sleep(50); 
              } catch (InterruptedException e) { 
                  e.printStackTrace(); 
              } 
          } 
          System.out.println("Сканирование завершено"); 
          }
      } 
 } 
