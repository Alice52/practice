package top.hubby.composite;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author T04856 <br>
 * @create 2023-03-08 10:07 AM <br>
 * @project project-cloud-custom <br>
 */
@Data
@AllArgsConstructor
public abstract class ComponentOrganization {
    private String name;

    public abstract void add(ComponentOrganization organization);

    public abstract ComponentOrganization getChild(String orgName);

    public abstract int getStaffCount();
}
