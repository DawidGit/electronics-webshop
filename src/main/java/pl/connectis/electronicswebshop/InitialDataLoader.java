package pl.connectis.electronicswebshop;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.connectis.electronicswebshop.persistence.dao.PrivilegeRepository;
import pl.connectis.electronicswebshop.persistence.dao.RoleRepository;
import pl.connectis.electronicswebshop.persistence.dao.UserRepository;
import pl.connectis.electronicswebshop.persistence.model.Privilege;
import pl.connectis.electronicswebshop.persistence.model.Role;
import pl.connectis.electronicswebshop.persistence.model.User;
import pl.connectis.electronicswebshop.products.Product;
import pl.connectis.electronicswebshop.products.ProductsRepository;

import java.util.*;

@Profile({"test", "dev"})
@Component
public class InitialDataLoader implements ApplicationRunner {

    private boolean alreadySetup = false;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PrivilegeRepository privilegeRepository;

    private final PasswordEncoder passwordEncoder;

    private final ProductsRepository productsRepository;

    public InitialDataLoader(@Qualifier("postgres") UserRepository userRepository, RoleRepository roleRepository, PrivilegeRepository privilegeRepository, PasswordEncoder passwordEncoder, ProductsRepository productsRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.passwordEncoder = passwordEncoder;
        this.productsRepository = productsRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (alreadySetup)
            return;
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        Privilege userManagmentPrivilege = createPrivilegeIfNotFound("USER_MANAGEMENT_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege, userManagmentPrivilege);
        List<Privilege> employeePrivileges = Arrays.asList(
                readPrivilege, writePrivilege);
        Role adminRole = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        Role customerRole = createRoleIfNotFound("ROLE_CUSTOMER", Collections.singletonList(readPrivilege));
        Role employeeRole = createRoleIfNotFound("ROLE_EMPLOYEE", employeePrivileges);

        User admin = new User();
        admin.setUsername("Admin");
        admin.setFirstName("Elon");
        admin.setLastName("Musk");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setEmail("e.musk@tesla.com");
        admin.setRoles(Collections.singletonList(adminRole));
        userRepository.save(admin);

        User customer = new User();
        customer.setUsername("Customer");
        customer.setFirstName("Keanu");
        customer.setLastName("Reeves");
        customer.setPassword(passwordEncoder.encode("customer"));
        customer.setEmail("mightybeaver2362@gmail.com.com");
        customer.setRoles(Collections.singletonList(customerRole));
        userRepository.save(customer);

        User employee = new User();
        employee.setUsername("Employee");
        employee.setFirstName("Adam");
        employee.setLastName("Smith");
        employee.setPassword(passwordEncoder.encode("employee"));
        employee.setEmail("a.smith@connectis.pl");
        employee.setRoles(Collections.singletonList(employeeRole));
        userRepository.save(employee);

        for (int i = 0; i < 9; i++) {
            Product product = new Product();
            product.setProductName("ProduktTest" + i + "Nazwa");
            product.setStock(new Random().nextInt(10) * i + 1);
            product.setAddedBy("Admin");
            productsRepository.save(product);
        }

        Product product2 = new Product();
        product2.setProductName("ProduktTest9Nazwa");
        product2.setStock(50);
        product2.setAddedBy("Employee");
        productsRepository.save(product2);

        alreadySetup = true;
    }

    private Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilege = privilegeRepository.save(privilege);
        }
        return privilege;
    }

    private Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            role = roleRepository.save(role);
        }
        return role;
    }
}
