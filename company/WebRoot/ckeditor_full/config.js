/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
    config.height = 120;
	config.toolbarGroups = [
    		//{ name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },
    		{ name: 'editing',     groups: [ 'find', 'selection', 'spellchecker' ] },
    		{ name: 'links' },
    		{ name: 'insert' },
    		//{ name: 'forms' },
    		{ name: 'tools' },
    		//{ name: 'document',	   groups: [ 'mode', 'document', 'doctools' ] },
    		{ name: 'others' },
    		'/',
    		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
    		{ name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ] },
    		{ name: 'styles' },
    		{ name: 'colors' },
    		{ name: 'about' }
	        ];
	
	//以下两句代码是为了实现ckeditor的图片上传功能。
	config.image_previewText=' '; //预览区域显示内容
	config.filebrowserImageUploadUrl= "img/uploadImgByCkeditor"; //待会要上传的action或servlet
};
