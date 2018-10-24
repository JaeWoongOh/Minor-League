import java.lang.reflect.ParameterizedType;

/**
 * Created by markj on 2018-10-17.
 */
public class Main {
    public static void main(String args[]){

        ControllImpl con = new ControllImpl(); // 1�� �̺�Ʈ�� ���� ���� ó��
        ControllImpl2 con2 = new ControllImpl2(); // 2�� �̺�Ʈ�� ���� ���� ó��

        Pro pro = new Pro();
        pro.bind(con);
        pro.bind(con2);

        /**������� �̺�Ʈ �߻�!!! ���ÿ� �ΰ��� �̺�Ʈ�� ����*/
        Keyboard keyboard = new Keyboard(); // ����� Ű���� �̺�Ʈ ����
        Sound sound = new Sound(); // ������� �Ҹ� �̺�Ʈ ����

        //�����
        con.put(keyboard);
        System.out.println("-------------------------------------");
        con2.put(sound);
    }
}
class Control<EventType>{
    public Pro pro;
    public EventType event;
    public String getTypeName(){
		// System.out.println(event.getClass().getName() + event.getClass().toString());
        return event.getClass().toString();
    }
    public void put(EventType eventType){
        this.event = eventType;
        pro.scan(this, event);
    }
}

class Keyboard {
    public void print(String str){
        System.out.println(str);
    }
}

class Sound {
    public void what(String str){
        System.out.println("�Ҹ�? : "+str);
    }
}

class ControllImpl extends Control<Keyboard>{

}

class ControllImpl2 extends Control<Sound>{

}

//�ڱⰡ ó���ϰ� ���� �̺�Ʈ�� ���ؼ� ���Ѵ�� �����
class Pro{
    PlugIn[] plugIns = {new Case1(), new Case2(), new Case3(), new Case4(), new Case5(), new Case6()};

    public void bind(Control con){
        con.pro = this;
    }

    public void scan(Control con, Object object){

        for(int i=0; i<plugIns.length; i++) {
            if (plugIns[i].getTypeName().equals(con.getTypeName())){
                plugIns[i].plug(object);
            }
        }
    }
}



abstract class PlugIn<EventType>{
    String name;
    protected PlugIn(){
        name = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0].toString();
    }

    public String getTypeName(){
        return name;
    }

    public abstract void plug(EventType eventType);
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////

//Ű���带 ó���ϴ� ���1
class Case1 extends PlugIn<Keyboard>{
    @Override
    public void plug(Keyboard event) {
        event.print("Ű���� ù��° ���");
    }
}

//Ű���带 ó���ϴ� �Ǵٸ� ���
class Case2 extends PlugIn<Keyboard>{
    @Override
    public void plug(Keyboard event) {
        event.print("Ű���� �ι�° ���");
    }
}

//���⼭ ���ʹ� �Ҹ��� ó��
class Case3 extends PlugIn<Sound>{
    @Override
    public void plug(Sound event) {
        event.what("����");
    }
}


class Case4 extends PlugIn<Sound>{
    @Override
    public void plug(Sound event) {
        event.what("�Ҵ�");
    }
}

class Case5 extends PlugIn<Sound>{
    @Override
    public void plug(Sound event) {
        event.what("����̴�");
    }
}

class Case6 extends PlugIn<Sound>{
    @Override
    public void plug(Sound event) {
        event.what("���̴�");
    }
}



