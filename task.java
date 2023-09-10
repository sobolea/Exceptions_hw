/*Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке, разделенные пробелом:
Фамилия Имя Отчество датарождения номертелефона пол
Форматы данных:
фамилия, имя, отчество - строки
датарождения - строка формата dd.mm.yyyy
номертелефона - целое беззнаковое число без форматирования
пол - символ латиницей f или m.
1.Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым, вернуть код ошибки, обработать его и 
показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.
2.Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. Если форматы данных не совпадают, 
нужно бросить исключение, соответствующее типу проблемы. Можно использовать встроенные типы java и создать свои. Исключение должно быть 
корректно обработано, пользователю выведено сообщение с информацией, что именно неверно.
3. Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну строку должны записаться полученные 
данные, вида <Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>
Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
Не забудьте закрыть соединение с файлом.
При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано, пользователь должен увидеть стектрейс ошибки.*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class task{
    public static void main(String[] args) {
       try {
            String[] data = inputData();
            Object[] info = new Object[6];
            info = formatData(data);
           
            PrintWriter writer = new PrintWriter(String.format("%s.txt", info[0]));
            for (Object object : info) {
                writer.println(object);
            }
            writer.close();
            //File save = new File("C:\\Users\\annas\\OneDrive\\Рабочий стол\\gb\\исключения\\hw\\hw", String.format("%s.txt", info[0]) );
       } catch (IncorrectSizeException e) {
        System.out.println(e);
       } catch (FormatException e) {
        System.out.println(e);
       } catch (FileNotFoundException e) {
        System.out.println(e);
       }
        
        
    }

    public static String[] inputData() throws IncorrectSizeException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите данные через пробел: фамилия, имя, отчество, дата рождения, номер телефона, пол");
   /*дописать */         System.out.println("В формате:\n...");   
        String str = sc.nextLine();
        sc.close();
        int size = str.split(" ").length;
        if(size == 6){
            return str.split(" ");
        } else {
            throw new IncorrectSizeException(String.format("Incorrect number of input data. Must be 6, but you input %d. Try one more time", size));
        }
        }

    public static Object[] formatData(String[] arr) throws FormatException{
        Object[] info = new Object[6];
        info[0]= arr[0];
        info[1]= arr[1];
        info[2]= arr[2];
        try {
            String[] date = arr[3].split("/");
            Calendar birth = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
            info[3] = birth;
            // System.out.println(birth.get(Calendar.YEAR));
        } catch (IllegalArgumentException e) {
            throw new FormatException(String.format("Incorrect input of data. Must be dd.mm.yyyy, you have %s", arr[3]));
        }
        try {
           int nuumber = Integer.parseInt(arr[4]);
           info[4] = nuumber;
        } catch (IllegalArgumentException e) {
            throw new FormatException("Incorrect input of phone number");
        }
        // System.out.println(arr[5]);
/* не работает         if(arr[5] == "f" || arr[5] == "m")*/ info[5]= arr[5];
       // else throw new FormatException(String.format("Must be f or m. You have %s", arr[5]));
        return info;
    }


}


//Как сделать чтобы после IncorrectSizeException можно было ещё  раз запустить считывание данных????

//Разделение даты через точку не работает

//Не считывает русский язык

//Не работает проверка пола

//Год считает +1