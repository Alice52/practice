package top.hubby.composite;

import lombok.extern.slf4j.Slf4j;

/**
 * @author T04856 <br>
 * @create 2023-03-08 10:09 AM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
public class ClientTest {
    private static ComponentOrganization constructOrganization() {
        // 构建总部
        CompositeOrganization root = new CompositeOrganization("总公司");
        LeafAdminDepartment headAdmin = new LeafAdminDepartment("总公司行政部");
        LeafItDepartment headIt = new LeafItDepartment("总公司It部");
        root.add(headAdmin);
        root.add(headIt);

        // 构建分公司
        CompositeOrganization branch1 = new CompositeOrganization("天津分公司");
        LeafAdminDepartment branch1Admin = new LeafAdminDepartment("天津分公司行政部");
        LeafItDepartment branch1It = new LeafItDepartment("天津分公司It部");
        branch1.add(branch1Admin);
        branch1.add(branch1It);

        // 将分公司加入到head中
        root.add(branch1);

        return root;
    }

    public static void main(String[] args) {

        ComponentOrganization org = constructOrganization();
        log.info(String.format("%s共有%d名员工", org.getName(), org.getStaffCount()));

        ComponentOrganization subOrg = org.getChild("天津分公司");
        log.info(String.format("%s共有%d名员工", subOrg.getName(), subOrg.getStaffCount()));

        ComponentOrganization subSubOrg = org.getChild("天津分公司It部");
        log.info(String.format("%s共有%d名员工", subSubOrg.getName(), subSubOrg.getStaffCount()));
    }
}
