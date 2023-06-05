package data;

public class Example {
    public static void main(String[] args) {
        WriteToJson json = new WriteToJson();
        WriteToJson.file = "test.json";
        WriteToJson.add(0, "John" ,10, 0);
        WriteToJson.add(1, "Jane" ,20, 0);
        WriteToJson.add(2, "Joe"  ,30, 0);
        WriteToJson.add(3, "Jill" ,40, 0);
        WriteToJson.add(4, "Jack" ,50, 0);

        int l = WriteToJson.dataList.size();
        for (int i = 0 ; i < l ; i++) {
            System.out.println(WriteToJson.dataList.get(i).toString());
        }
        WriteToJson.save();
    }
}
