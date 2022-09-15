package hexlet.code.controllers;

import hexlet.code.UrlHandler;
import hexlet.code.domain.Url;
import hexlet.code.domain.query.QUrl;
import io.javalin.http.Handler;
import io.javalin.http.NotFoundResponse;
import io.ebean.PagedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;


public final class UrlController {
    public static Handler listUrls = ctx -> {
        int page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1) - 1;
        int rowsPerPage = 10;
        PagedList<Url> pagedUrls = new QUrl()
                .setFirstRow(page * rowsPerPage)
                .setMaxRows(rowsPerPage)
                .orderBy()
                .id.asc()
                .findPagedList();
        List<Url> urls = pagedUrls.getList();
        int lastPage = pagedUrls.getTotalPageCount() + 1;
        int currentPage = pagedUrls.getPageIndex() + 1;
        List<Integer> pages = IntStream
                .range(1, lastPage)
                .boxed()
                .collect(Collectors.toList());
        ctx.attribute("urls", urls);
        ctx.attribute("pages", pages);
        ctx.attribute("currentPage", currentPage);
        ctx.render("listUrls.html");
    };

    public static Handler showUrl = ctx -> {
        int id = ctx.pathParamAsClass("id", Integer.class).getOrDefault(null);
        Url url = new QUrl()
                .id.equalTo(id)
                .findOne();
        if (url == null) {
            throw new NotFoundResponse();
        }
        ctx.attribute("url", url);
        ctx.render("showUrl.html");
    };

    public static Handler createUrl = ctx -> {
        String name = UrlHandler.getUrl(ctx.formParam("url"));
        if (name == null) {
            ctx.sessionAttribute("flash", "Некорректный URL");
            ctx.sessionAttribute("flash-type", "danger");
            ctx.redirect("/");
            return;
        }

        Url url = new QUrl()
                .name.equalTo(name)
                .findOne();
        if (url != null) {
            ctx.sessionAttribute("flash", "Страница уже существует");
            ctx.sessionAttribute("flash-type", "danger");
            ctx.redirect("/urls");
            return;
        }

        Url newUrl = new Url(name);
        newUrl.save();
        ctx.sessionAttribute("flash", "Страница успешно добавлена");
        ctx.sessionAttribute("flash-type", "success");
        ctx.redirect("/urls");
    };

    public static Handler checkUrl = ctx -> {
//        int id = ctx.pathParamAsClass("id", Integer.class).getOrDefault(null);
//        Url url = new QUrl()
//                .id.equalTo(id)
//                .findOne();
//        if (url == null) {
//            throw new NotFoundResponse();
//        }
//        url.addUrlChecks(UrlHandler.checkUrl(url));
//        url.update();
//        ctx.attribute("url", url);
//        ctx.render("showUrl.html");
        ctx.redirect("/urls");
    };

}

