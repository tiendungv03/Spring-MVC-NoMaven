package main.java.com.exam.service;

import main.java.com.exam.model.Student;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StudentFileService {

    private static final String TXT_PATH  = "/home/tiendungv03/students.txt";
    private static final String JSON_PATH = "/home/tiendungv03/students.json";
    private static final String XML_PATH  = "/home/tiendungv03/students.xml";

    // ===== TXT: mỗi dòng: id;name;email;score =====
    public void saveToTxt(List<Student> list) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TXT_PATH))) {
            for (Student s : list) {
                bw.write(s.getId() + ";" + s.getName() + ";" + s.getEmail() + ";" + s.getScore());
                bw.newLine();
            }
        }
    }

    public List<Student> readFromTxt() throws IOException {
        List<Student> list = new ArrayList<>();
        File f = new File(TXT_PATH);
        if (!f.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    Student s = new Student();
                    s.setId(Integer.parseInt(parts[0]));
                    s.setName(parts[1]);
                    s.setEmail(parts[2]);
                    s.setScore(Double.parseDouble(parts[3]));
                    list.add(s);
                }
            }
        }
        return list;
    }

    // ===== JSON: dùng Gson =====
    public void saveToJson(List<Student> list) throws IOException {
        Gson gson = new Gson();
        try (Writer w = new FileWriter(JSON_PATH)) {
            gson.toJson(list, w);
        }
    }

    public List<Student> readFromJson() throws IOException {
        File f = new File(JSON_PATH);
        if (!f.exists()) return new ArrayList<>();

        Gson gson = new Gson();
        try (Reader r = new FileReader(f)) {
            Type type = new TypeToken<List<Student>>(){}.getType();
            List<Student> list = gson.fromJson(r, type);
            return list != null ? list : new ArrayList<>();
        }
    }

    // ===== XML: làm kiểu đơn giản, tự build string =====
    public void saveToXml(List<Student> list) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(XML_PATH))) {
            bw.write("<students>\n");
            for (Student s : list) {
                bw.write("  <student>\n");
                bw.write("    <id>" + s.getId() + "</id>\n");
                bw.write("    <name>" + s.getName() + "</name>\n");
                bw.write("    <email>" + s.getEmail() + "</email>\n");
                bw.write("    <score>" + s.getScore() + "</score>\n");
                bw.write("  </student>\n");
            }
            bw.write("</students>\n");
        }
    }

    public List<Student> readFromXml() throws IOException {
        // để đơn giản, có thể chỉ demo saveToXml, đọc thì bỏ qua hoặc parse đơn giản
        return new ArrayList<>();
    }
}
