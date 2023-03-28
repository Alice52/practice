package top.hubby.composite;

/**
 * @author T04856 <br>
 * @create 2023-03-08 10:10 AM <br>
 * @project project-cloud-custom <br>
 */
public class LeafAdminDepartment extends ComponentOrganization {

    public LeafAdminDepartment(String name) {
        super(name);
    }

    @Override
    public int getStaffCount() {
        return 20;
    }

    @Override
    public void add(ComponentOrganization organization) {
        throw new UnsupportedOperationException(this.getName() + "已经是最基本部门，无法增加下属部门");
    }

    @Override
    public ComponentOrganization getChild(String orgName) {
        if (getName().equals(orgName)) {
            return this;
        }
        return null;
    }
}
