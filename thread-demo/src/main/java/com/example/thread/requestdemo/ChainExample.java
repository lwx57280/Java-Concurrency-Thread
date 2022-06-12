package com.example.thread.requestdemo;
/**
 * @description:    阻塞队列的案例
 * @author:         cong zhi
 * @createDate:     2022/6/11 16:05
 * @updateUser:     cong zhi
 * @updateDate:     2022/6/11 16:05
 * @updateRemark:   修改内容
 * @version:        v1.0
 */
public class ChainExample {

    public static void main(String[] args) {
        FinalRequestProcessor finalRequestProcessor = new FinalRequestProcessor();
        SaveRequestProcessor saveRequestProcessor = new SaveRequestProcessor(finalRequestProcessor);
        saveRequestProcessor.start();

        PrintProcessor printProcessor = new PrintProcessor(saveRequestProcessor);
        printProcessor.start();

        ValidProcessor validProcessor = new ValidProcessor(printProcessor);
        validProcessor.start();

        Request request = new Request();
        request.setName("lisa");
        validProcessor.processorRequest(request);

    }
}
