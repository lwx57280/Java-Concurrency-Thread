package com.example.concurrency.publish;

import com.example.concurrency.annoations.NotRecommend;
import com.example.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    /**
     * 发布对象：使一个对象能够被当前范围之外的代码所使用
     *
     * 对象溢出：一种错误的发布。当一个对象还没有构造完成时，就使它被其他线程所见。
     */
    private int thisCanBeEscape =0;

    public Escape() {
        new InnerClass();
    }

    private class InnerClass{

        public InnerClass() {
            log.info("{}",Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }

}
