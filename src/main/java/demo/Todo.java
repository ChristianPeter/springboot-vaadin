package demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.io.Serializable;
//import org.apache.commons.beanutils.BeanUtils;

@Entity
public class Todo implements Serializable, Cloneable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String subject;
	private boolean done;
	
	
	protected Todo(){}
	
	public Todo(String t){
		this.subject = t;
	}
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public String getSubject(){
		return subject;
	}
	
	public void setSubject(String s){
		this.subject = s;
	}
	
	public boolean isDone(){
		return done;
	}
	
	public void setDone(boolean b){
		this.done = b;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", subject=" + subject + ", done=" + done + "]";
	}
	
	
	
	/*
	@Override
    public Todo clone() throws CloneNotSupportedException {
        try {
            return (Todo) BeanUtils.cloneBean(this);
        } catch (Exception ex) {
            throw new CloneNotSupportedException();
        }
    }
    */
}
