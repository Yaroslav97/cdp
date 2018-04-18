package entity;

public class Subject {

    private long id;
    private String subjectName;
    private String tutor;

    public Subject() {
    }

    public Subject(long id, String subjectName, String tutor) {
        this.id = id;
        this.subjectName = subjectName;
        this.tutor = tutor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Subject{");
        sb.append("id=").append(id);
        sb.append(", subjectName='").append(subjectName).append('\'');
        sb.append(", tutor='").append(tutor).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
