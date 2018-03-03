package com.example.vankien.learndatetimepicker;

/**
 * Created by vankien on 3/1/18.
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/**
 * Class được định nghĩa công việc phải hoành thành
 * trong một tuần
 * @author drthanh
 *
 */
public class WorkModel {
    private String title;
    private String desciption;
    private Date dateFinish;
    private Date hourFinish;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDesciption() {
        return desciption;
    }
    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }
    public Date getDateFinish() {
        return dateFinish;
    }
    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }
    public Date getHourFinish() {
        return hourFinish;
    }
    public void setHourFinish(Date hourFinish) {
        this.hourFinish = hourFinish;
    }
    public WorkModel(String title, String desciption, Date dateFinish,
                     Date hourFinish) {
        super();
        this.title = title;
        this.desciption = desciption;
        this.dateFinish = dateFinish;
        this.hourFinish = hourFinish;
    }
    public WorkModel() {
        super();
    }
    public String getDateFormat(Date d)
    {
        SimpleDateFormat dft=new
                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dft.format(d);
    }
    public String getHourFormat(Date d)
    {
        SimpleDateFormat dft=new
                SimpleDateFormat("HH:mm", Locale.getDefault());
        return dft.format(d);
    }
    @Override
    public String toString() {
        return this.title+"-"+
                getDateFormat(this.dateFinish)+"-"+
                getHourFormat(this.hourFinish);
    }
}
