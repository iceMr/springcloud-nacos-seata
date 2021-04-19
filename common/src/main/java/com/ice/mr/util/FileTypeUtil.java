package com.ice.mr.util;

public class FileTypeUtil {
    public   static  String  getFileType(String filesuffixStr){
        String  fileType=null;
        switch(filesuffixStr){
            case  "png" :  fileType ="image" ;break;
            case  "jpg" :  fileType ="image" ;break;
            case  "jpeg" : fileType ="image" ;break;
            case  "gif" :  fileType ="image" ;break;
            case  "bmp" :  fileType ="image" ;break;
            case  "mp4" :  fileType ="video" ;break;
            case  "rmvb" :  fileType ="video";break;
            case  "mkv" :  fileType ="video" ;break;
            case  "wmv" :  fileType ="video" ;break;
            case  "flv" :  fileType ="video" ;break;
            case  "mp3" :  fileType ="audio" ;break;
            case  "wav" :  fileType ="audio" ;break;
            case  "wma" :  fileType ="audio" ;break;
            case  "ogg" :  fileType ="audio" ;break;
            case  "aac" :  fileType ="audio" ;break;
            case  "doc" :  fileType ="document" ;break;
            case  "docx" :  fileType ="document" ;break;
            case  "xls" :  fileType ="document" ;break;
            case  "xlsx" :  fileType ="document" ;break;
            case  "ppt" :  fileType ="document" ;break;
            case  "pptx" :  fileType ="document" ;break;
            case  "pdf" :  fileType ="document" ;break;
            case  "txt" :  fileType ="document" ;break;
            case  "tif" :  fileType ="document" ;break;
            case  "tiff" :  fileType ="document" ;break;
        }
        return  fileType;
    }

}
