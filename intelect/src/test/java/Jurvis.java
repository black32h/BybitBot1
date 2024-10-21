public class Jurvis {
    public void sayHi (String name){
        System.out.println("Добрый вечер" + name + ", как дела?");
    }
    public static void main (String args){
        Jurvis jurvis = new Jurvis();
        jurvis.sayHi("Тони Старк");
    }
}
