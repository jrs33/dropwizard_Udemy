package com.innovateEDU.resources;

import com.innovateEDU.core.Bookmark;
import com.innovateEDU.db.BookmarkDAO;
import com.innovateEDU.core.User;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.common.base.Optional;
import io.dropwizard.jersey.params.LongParam;

import java.util.List;

// DB backed resource example
@Path("/bookmarks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookmarksResource {

    private BookmarkDAO dao;

    public BookmarksResource(BookmarkDAO dao) {
        this.dao = dao;
    }

    @GET
    @UnitOfWork
    public List<Bookmark> getBookmarks(@Auth User user) {
        return dao.findForUser(user.getId());
    }

    // Checks to see that the bookmark belongs to the user
    private Optional<Bookmark> findIfAuthorized(
            long bookmarkId,
            long userId
    ) {
        Optional<Bookmark> result = dao.findById(bookmarkId);

        if(result.isPresent() && userId != result.get().getUser().getId()) {
            throw new ForbiddenException("UNAUTHORIZED");
        }

        return result;
    }

    @GET
    @Path("/{id}")
    @UnitOfWork // method uses database so we need to create a session
    public Optional<Bookmark> getBookmark(@PathParam("id") LongParam id,
                                          @Auth User user) {
        return findIfAuthorized(id.get(), user.getId());
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public Optional<Bookmark> delete(@PathParam("id") LongParam id,
                                     @Auth User user) {
        Optional<Bookmark> bookmark = findIfAuthorized(id.get(), user.getId());

        if(bookmark.isPresent()) {
            dao.delete(bookmark.get());
        }
        return bookmark;
    }

    @POST
    @UnitOfWork
    public Bookmark saveBookmark(Bookmark bookmark,
                                 @Auth User user) {
        bookmark.setUser(user);
        return dao.save(bookmark);
    }

}
