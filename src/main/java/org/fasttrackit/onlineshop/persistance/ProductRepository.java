package org.fasttrackit.onlineshop.persistance;

import org.fasttrackit.onlineshop.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByNameContaining(String partialName, Pageable pageable);

    Page<Product> findByNameContainingAndAndQuantityGreaterThanEqual(String parialName, int minQuantity,
                                                                     Pageable pageable);

    //JPQL syntax (java persistence query language)
    // @Query ("SELECT * FROM Product product WHERE name LIKE '%:partialName%'")
    //Native MySQL query
    // daca avem  nume de tabele sau coloane care coincid cu reserved keywords il incadram intre simbolurile tilda
    @Query(value = "SELECT * FROM product WHERE `name` LIKE '%?0%'", nativeQuery = true)
    Page<Product> findByPatialName(String partialName, Pageable pageable);
}
