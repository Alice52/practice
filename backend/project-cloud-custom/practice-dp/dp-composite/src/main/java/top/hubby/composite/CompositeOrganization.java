package top.hubby.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author T04856 <br>
 * @create 2023-03-08 10:08 AM <br>
 * @project project-cloud-custom <br>
 */
public class CompositeOrganization extends ComponentOrganization {

    // 很关键，这体现了组合的思想
    private List<ComponentOrganization> organizations = new ArrayList<>();

    public CompositeOrganization(String name) {
        super(name);
    }

    @Override
    public void add(ComponentOrganization organization) {
        organizations.add(organization);
    }

    @Override
    public ComponentOrganization getChild(String orgName) {
        for (ComponentOrganization org : organizations) {
            if (orgName.equals(org.getName())) {
                return org;
            }

            ComponentOrganization targetOrg = org.getChild(orgName);
            if (targetOrg != null) {
                return targetOrg;
            }
        }
        return null;
    }

    @Override
    public int getStaffCount() {
        int count = 0;
        for (ComponentOrganization organization : organizations) {
            count += organization.getStaffCount();
        }
        return count;
    }
}
