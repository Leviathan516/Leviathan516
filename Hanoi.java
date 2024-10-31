import java.util.Stack;

public class Hanoi {
    public static final int NUM_PILLARS = 3; 
    public Stack<Integer>[] pillars;
    public int startPillar;
    public int targetPillar;
    public int timeStep;

    @SuppressWarnings("unchecked")
    public Hanoi(int numDisks, int startPillar, int targetPillar) {
        if (numDisks <= 0) {
            throw new IllegalArgumentException("Number of disks must be positive.");
        }
        if (startPillar < 0 || startPillar >= NUM_PILLARS || targetPillar < 0 || targetPillar >= NUM_PILLARS) {
            throw new IllegalArgumentException("Pillar indices must be within range [0, " + (NUM_PILLARS - 1) + "].");
        }
        this.startPillar = startPillar;
        this.targetPillar = targetPillar;
        this.timeStep = 0;
        pillars = new Stack[NUM_PILLARS];
        for (int i = 0; i < NUM_PILLARS; i++) {
            pillars[i] = new Stack<>();
        }

        for (int i = numDisks; i > 0; i--) {
            pillars[startPillar].push(i);
        }

        System.out.println("My Solution is:");
        printPillars();
    }

    public void start() {
        solve(pillars[startPillar].size(), this.startPillar, this.targetPillar, NUM_PILLARS - this.startPillar - this.targetPillar);
    }

    private void solve(int n, int from, int to, int aux) {
        if (n == 1) {
            moveDisk(from, to);
            return;
        }
        solve(n - 1, from, aux, to);
        moveDisk(from, to);
        solve(n - 1, aux, to, from);
    }

    private void moveDisk(int from, int to) {
        if (pillars[from].isEmpty()) {
            throw new IllegalStateException("Attempting to move a disk from an empty pillar.");
        }
        int disk = pillars[from].pop();
        pillars[to].push(disk);
        timeStep++;
        printPillars();
    }

    private void printPillars() {
        System.out.println("t" + timeStep + "\tPillar1: " + formatPillar(0));
        System.out.println("t" + timeStep + "\tPillar2: " + formatPillar(1));
        System.out.println("t" + timeStep + "\tPillar3: " + formatPillar(2));
        System.out.println();
    }

    private String formatPillar(int pillarIndex) {
        StringBuilder sb = new StringBuilder();
        for (int disk : pillars[pillarIndex]) {
            sb.append(disk).append(" ");
        }
        return sb.toString().trim();
    }
}
