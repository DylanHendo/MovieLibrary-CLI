
public class MemberCollection {

    private final int CAPACITY = 10;
    private int numMembers;
    Member[] members = new Member[CAPACITY];    // array of members assuming the maximal number of members is 10

    /**
     * Ensure there is still space left in member array to add new members
     */
    private void ensureCapacity() {
        if (numMembers == members.length) {
            int newSize = members.length + 1;
            Member[] newArr = new Member[newSize];

            System.arraycopy(members, 0, newArr, 0, members.length);
            members = newArr;
        }
    }

    /**
     * Add a new member to the member collection.
     * @param newMember The new member to be added to the array
     */
    public void add(Member newMember) {
        ensureCapacity();
        if (numMembers < CAPACITY)
            members[numMembers++] = newMember;
    }

    /**
     * Finds phone number belonging to user with firstName and lastName.
     * @param firstName First name of member whose phone number is being searched for
     * @param lastName Last name of member whose phone number is being searched for
     * @throws NullPointerException Catch error if empty array, do nothing
     */
    public void findPhoneNum(String firstName, String lastName) throws NullPointerException{
        String fullName = lastName + firstName;

        for (Member member : members) {
            try {
                if (member.getFullName().equals(fullName)) {
                    System.out.println(member.getPhoneNumber());
                    return;
                }
            } catch (NullPointerException ignored) { }
        }
        System.out.println("Not Found");
    }

    /**
     * Searches member collection for user with fullName.
     * @param fullName Username of user (lastName + firstName)
     * @return True if member collection contains fullName, false otherwise
     * @throws NullPointerException Catch error if empty array, do nothing
     */
    public boolean contains(String fullName) throws NullPointerException {
        for (Member member : members) {
            try {
                if (member.getFullName().equals(fullName))
                    return true;
            } catch (NullPointerException ignored) { }
        }
        return false;
    }

    /**
     * Find password belonging to user with username as fullName
     * @param fullName Last name + first name of user
     * @return Password belonging to user with username=fullName
     * @throws NullPointerException Catch error if empty array, do nothing
     */
    public String findPassword(String fullName) throws NullPointerException {
        for (Member member : members) {
            try {
                if (member.getFullName().equals(fullName))
                    return member.getPassword();
            } catch (NullPointerException ignored) { }
        }
        return "";
    }

    /**
     * Find Member object that correlates to fullName
     * @param fullName lastName + firstName of member being searched for
     * @return Member object that has username: fullName
     * @throws NullPointerException Catch error if empty array, do nothing
     */
    public Member findMember(String fullName) throws NullPointerException {
        for (Member member : members) {
            try {
                if (member.getFullName().equals(fullName))
                    return member;
            } catch (NullPointerException ignored) { }
        }
        return null;
    }

    // =============== GETTER FUNCTIONS ================
    public int getNumMembers() {return numMembers; }
    public int getCAPACITY() { return CAPACITY; }

}
