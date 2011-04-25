package edu.kubsu.fpm;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class TestBean {
    private List<Person> values = new ArrayList<Person>();
    private String selectedId;

    public TestBean(){
        values.add(new Person("pId1", "Ivanov Ivan"));
        values.add(new Person("pId2", "Petrov Petr"));
    }

    public void linkListener(ActionEvent evt){
        String name = (String) evt.getComponent().getAttributes().get("value");
        String id = selectedId;
        String id2 = (String) evt.getComponent().getAttributes().get("id");
    }

    public String action(){
        String id = selectedId;

        return null;
    }

    public List<Person> getValues() {
        return values;
    }

    public void setValues(List<Person> values) {
        this.values = values;
    }

    public String getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(String selectedId) {
        this.selectedId = selectedId;
    }

    public class Person{
        private String id;
        private String name;

        public Person (String id, String name){
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
