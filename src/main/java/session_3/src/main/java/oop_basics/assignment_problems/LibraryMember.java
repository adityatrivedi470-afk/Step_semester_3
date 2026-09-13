package oop_basics.assignment_problems;

public class LibraryMember {
    private String name;
    private String memberId;
    private int booksIssued;

    private static String libraryName = "City Library";
    private static int memberCount = 0;

    public LibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
        memberCount++;
        this.memberId = "LM-" + (1000 + memberCount);
    }

    public void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    public static void printTotalMembers() {
        System.out.println("Total members: " + memberCount);
    }

    static class BrokenLibraryMember {
        static String name;
        static String memberId;
        static int booksIssued;

        BrokenLibraryMember(String name, String memberId, int booksIssued) {
            BrokenLibraryMember.name = name;
            BrokenLibraryMember.memberId = memberId;
            BrokenLibraryMember.booksIssued = booksIssued;
        }
    }

    public static void main(String[] args) {
        BrokenLibraryMember b1 = new BrokenLibraryMember("Aditi", "LM-01", 2);
        BrokenLibraryMember b2 = new BrokenLibraryMember("Rohan", "LM-02", 4);
        System.out.println(BrokenLibraryMember.name);
        System.out.println(BrokenLibraryMember.name);
        System.out.println("(Aditi's data was overwritten — both members now show “Rohan”)");

        LibraryMember m1 = new LibraryMember("Aditi", 2);
        LibraryMember m2 = new LibraryMember("Rohan", 4);
        m1.printMemberCard();
        m2.printMemberCard();
        printTotalMembers();
    }
}
