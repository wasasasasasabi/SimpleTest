//import com.github.javafaker.Faker;
//import lombok.SneakyThrows;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.util.Locale;
//
//public class InsertFakeDataToDB {
//
//
//    @SneakyThrows
//    public static void main(String[] args) {
//        //先写1000w条数据进文件
//        Faker faker = new Faker(new Locale("zh-cn"));
//        System.out.println(faker.idNumber().invalid());
//        System.out.println(faker.name().fullName());
//        System.out.println(faker);
////        FileWriter writer = new FileWriter(new File("C:\\Users\\宏基\\Desktop"));
////        for (int i = 0; i < 10000000; i++) {
////            StringBuilder stringBuilder = new StringBuilder();
////            stringBuilder.append(String.valueOf(faker.idNumber()))
////            writer.write();
////            writer.write(System.getProperty("line.separator"));
////        }
////        writer.close();
//    }
//}
