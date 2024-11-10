package com.aem.testsite.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aem.testsite.core.models.BlogPost;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BlogListingModel {

    @Self
    private Resource resource;
    private final Logger logger = LoggerFactory.getLogger(BlogListingModel.class);

    private List<BlogPost> blogPosts;

    @PostConstruct
    protected void init() {
        blogPosts = new ArrayList<>();

        // Get the resource of the main blog page's child pages
        Resource blogPostsResource = resource.getResourceResolver().getResource("/content/aemtestsite/us/blog-pages");
        if (blogPostsResource != null) {
            for (Resource postResource : blogPostsResource.getChildren()) {
                if (!(postResource.getPath().endsWith("jcr:content"))) {
                    String path = postResource.getPath() + "/jcr:content";
                    String link = postResource.getPath() + ".html";
                    Resource contentResource = resource.getResourceResolver().getResource(path);
                    
                    if (contentResource != null) {
                        String title = contentResource.getValueMap().get("jcr:title", String.class);
                        if (title != null) {
                            blogPosts.add(new BlogPost(title, link));
                        }
                    }
                }
            }
        }

        // Debug output to check if titles and links are being fetched
        logger.info("Blog posts: {}", blogPosts);
    }

    public List<BlogPost> getBlogPosts() {
        return blogPosts;
    }
}
