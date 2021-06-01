package Task_1;

class ProcessState {
    public static String State(Thread.State ts) {
        if (ts == Thread.State.BLOCKED){
            return "BLOCKED";
        }
        if (ts == Thread.State.NEW){
            return "NEW";
        }
        if (ts == Thread.State.RUNNABLE){
            return "RUNNABLE";
        }
        if (ts == Thread.State.TIMED_WAITING){
            return "TIMED_WAITING";
        }
        if (ts == Thread.State.WAITING){
            return "Waiting";
        }
        return "TERMINATED";

    }
}
