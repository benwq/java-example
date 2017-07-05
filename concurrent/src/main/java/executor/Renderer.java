package executor;

import entities.Ad;
import entities.ImageData;
import entities.ImageInfo;
import entities.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * Created by benwq on 2017/7/4.
 * 模拟html加载文本及图片过程
 */
public class Renderer {
    private final ExecutorService executorService;

    private static final long TIME_BUDGET = 1000;

    private static final Ad DEFAULT_AD = new Ad();

    public Renderer(ExecutorService executorService) {
        this.executorService = executorService;
    }

    void renderPage(CharSequence source){
        List<ImageInfo> info = scanForImageInfo(source);
        CompletionService<ImageData> completionService = new ExecutorCompletionService<ImageData>(executorService);
        for(final ImageInfo imageInfo:info){
            completionService.submit(new Callable<ImageData>() {
                @Override
                public ImageData call() throws Exception {
                    return imageInfo.downloadImage();
                }
            });
        }
        renderText(source);

        try {
            for (int t = 0, n = info.size(); t < n; t++) {
                Future<ImageData> f = completionService.take();
                ImageData imageData  = f.get();
                renderImage(imageData);
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        catch (ExecutionException e){
            e.getCause();
        }
    }

    void renderText(CharSequence source){

    }

    void renderImage(ImageData imageData){

    }

    Page renderPageWithAd() throws InterruptedException{
       long endNanos = System.nanoTime() + TIME_BUDGET;
        Page page = renderPageBody();
        Ad ad;
        Future<Ad> f = executorService.submit(new Callable<Ad>(){
            @Override
            public Ad call() throws Exception {
                return new Ad();
            }
        });
        try {
            long timeLeft = endNanos-System.nanoTime();
            ad = f.get(timeLeft,NANOSECONDS);
        } catch (ExecutionException e) {
            ad = DEFAULT_AD;
            e.printStackTrace();
        } catch (TimeoutException e) {
            ad = DEFAULT_AD;
            f.cancel(true);             //可在运行过程中取消
            e.printStackTrace();
        }
        page.setAd(ad);
        return page;
    }

    Page renderPageBody(){
        return new Page();
    }

    List<ImageInfo> scanForImageInfo(CharSequence sequence){
        return new ArrayList<ImageInfo>();
    }
}
