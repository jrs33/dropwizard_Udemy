package com.innovateEDU.db;

import com.innovateEDU.core.Bookmark;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import com.google.common.base.Optional;

public class BookmarkDAO extends AbstractDAO<Bookmark> {

    public BookmarkDAO(SessionFactory sessionFactory) {

        super(sessionFactory);

    }

    public List<Bookmark> findForUser(long id) {

        return list(
                namedQuery("com.innovateEDU.core.Bookmark.findForUser")
                        .setParameter("id", id)
        );

    }

    public Optional<Bookmark> findById(long id) {

        return Optional.fromNullable(get(id)); // super class method

    }

    public Bookmark save(Bookmark bookmark) {

        return persist(bookmark);

    }

    public void delete(Bookmark bookmark) {

        namedQuery("com.innovateEDU.core.Bookmark.remove")
                .setParameter("id", bookmark.getId())
                .executeUpdate();

    }

}
