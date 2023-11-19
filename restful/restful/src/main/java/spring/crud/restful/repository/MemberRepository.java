package spring.crud.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.crud.restful.model.Member;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(value = "SELECT *\n" +
            "FROM tbl_member\n" +
            "ORDER BY member_id\n" +
            "LIMIT :itemsPerPage OFFSET :startIndex", nativeQuery = true)
    List<Member> findAll(@Param("itemsPerPage") int itemsPerPage, @Param("startIndex") int startIndex);

    @Query(value = "SELECT count(*)\n" +
            "FROM tbl_member", nativeQuery = true)
    int countMember();

    @Query(value = "SELECT * FROM tbl_member WHERE member_id =:memberId AND block =:block", nativeQuery = true)
    Optional<Member> findByIdAndBlock(@Param("memberId") Long memberId, @Param("block") boolean block);
}
