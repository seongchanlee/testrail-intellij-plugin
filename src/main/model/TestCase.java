package main.model;

public class TestCase {

    private String id;
    private String title;
    private String prerequisites;
    private String steps;
    private String expectedResults;

    private TestCase(Builder builder){
        id = builder.id;
        title = builder.title;
        prerequisites = builder.prerequisites;
        steps = builder.steps;
        expectedResults = builder.expectedResults;
    }

    public static class Builder {
        private String id;
        private String title;
        private String prerequisites;
        private String steps;
        private String expectedResults;

        public Builder(String id, String title){
            this.id = id;
            this.title = title;
        }

        public Builder setPrerequisites(String prerequisites){
            this.prerequisites = prerequisites;
            return this;
        }

        public Builder setSteps(String steps){
            this.steps = steps;
            return this;
        }

        public Builder setExpectedResults(String expectedResults){
            this.expectedResults = expectedResults;
            return this;
        }

        public TestCase build(){
            return new TestCase(this);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getExpectedResults() {
        return expectedResults;
    }

    public void setExpectedResults(String expectedResults) {
        this.expectedResults = expectedResults;
    }
}
