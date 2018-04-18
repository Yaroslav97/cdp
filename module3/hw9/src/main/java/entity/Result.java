package entity;

public class Result {

    private long id;
    private long studentID;
    private long subjectID;
    private byte mark;

    public Result() {
    }

    public Result(long id, long studentID, long subjectID, byte mark) {
        this.id = id;
        this.studentID = studentID;
        this.subjectID = subjectID;
        this.mark = mark;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }

    public long getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(long subjectID) {
        this.subjectID = subjectID;
    }

    public byte getMark() {
        return mark;
    }

    public void setMark(byte mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Result{");
        sb.append("id=").append(id);
        sb.append(", studentID=").append(studentID);
        sb.append(", subjectID=").append(subjectID);
        sb.append(", mark=").append(mark);
        sb.append('}');
        return sb.toString();
    }

}
