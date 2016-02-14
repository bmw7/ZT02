package com.mavict.utils;

import java.util.regex.*;
import java.util.List;
import java.util.ArrayList;

/**
 * 实现类似PHP中的strip_tags()函数功能 
 * 使用方法：StripTagsUtils.parse(String html)
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
public final class StripTagsUtils {
	
	    /** 不可实例化  */
		private StripTagsUtils() {
			
		}

        private static  StripTagsRegExp reg = null;
        
        static{
        	reg = new StripTagsRegExp();
        }

        public static String parse(String html)
        {
                return parse( html,null );
        }

        public static String parse(String html,String allows )
        {
                if( allows == null || allows.trim().length() == 0 )
                {
                        html = reg.buildHTMLScriptRegex().replaceAll(html,"");
                        html = reg.buildHTMLCssRegex().replaceAll(html,"");
                        html = reg.buildHTMLTagRegex().replaceAll(html,"");        
                        return html;
                }
                String tagre = "<([^>]*)>";        
                List<String> gs = reg.buildRegex(tagre).matcher(allows);
                String pr = "";                
                for(String g:gs)
                {
                        pr = "(<"+g+"[^>]*>(?:\\s|\\S)*?</[^>]+>)";
                        html = reg.buildRegex(pr).replaceAll(html,"<","####&lt####");                        
                }                
                html = reg.buildHTMLTagRegex().replaceAll(html,"");
                html = html.replaceAll("####&lt####","<");
                
                return html;
        }        

        static class StripTagsRegExp
        {
                private final String htmltag = "<[^>]*>";
                private final String htmlscript = "<script[^>]*>(?:\\s|\\S)*?</script>";
                private final String htmlcss = "<style[^>]*>(?:\\s|\\S)*?</style>";

                private Pattern p;
                private Matcher m;

                public StripTagsRegExp buildHTMLTagRegex()
                {
                        this.p = Pattern.compile(htmltag,Pattern.CASE_INSENSITIVE);

                        return this;
                }

                public StripTagsRegExp buildHTMLScriptRegex()
                {
                        this.p = Pattern.compile(htmlscript,Pattern.CASE_INSENSITIVE);
                        return this;
                }

                public StripTagsRegExp buildHTMLCssRegex()
                {
                        this.p = Pattern.compile(htmlcss,Pattern.CASE_INSENSITIVE);
                        return this;
                }

                public StripTagsRegExp buildRegex(String reg)
                {
                        this.p = Pattern.compile(reg,Pattern.CASE_INSENSITIVE);
                        return this;
                }

                public StripTagsRegExp buildRegex(Pattern p)
                {
                        this.p = p;
                        return this;
                }

                public List<String> matcher(String str)
                {
                        List<String> gs = new ArrayList<String>();

                        m = p.matcher(str);
                        int count = 0;
                        int i=0;
                        while( m.find() )
                        {
                                count = m.groupCount();
                                for(i=1;i<=count;i++)
                                {
                                        gs.add(m.group(i));
                                }
                        }
                        m = null;

                        return gs;
                }

                public String replace(String str,String rstr)
                {
                        return str.replace(p.toString(),rstr);
                }

                public String replaceAll(String str,String rstr)
                {
                        return str.replaceAll(p.toString(),rstr);
                }

                public String replaceAll(String str,String i,String r )
                {
                        m = p.matcher(str);
                        String g = "";
                        while( m.find() )
                        {
                                g = m.group();                        
                                str = str.replaceAll(g,g.replaceAll(i,r));                
                        }
                        m = null;
                        return str;
                }

                public boolean test(String str)
                {
                        m = p.matcher(str);
                        return m.find();
                }
        }
}
