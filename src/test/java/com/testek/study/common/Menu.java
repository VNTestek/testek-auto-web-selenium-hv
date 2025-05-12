package com.testek.study.common;

import lombok.Getter;

@Getter
public enum Menu {
    ELEMENTS("menu-elements"),
    TEXT_BOX("submenu-textbox"),
    CHECK_BOX("submenu-checkbox"),
    RADIO_BUTTON("submenu-radio"),
    WEB_TABLES("submenu-webtables"),
    BUTTONS("submenu-buttons"),
    LINKS("submenu-links"),
    BROKEN_LINKS("submenu-brokenlinks"),
    UPLOAD_AND_DOWNLOAD("submenu-upload"),
    DYNAMIC_PROPERTIES("submenu-dynamic"),

    FORM("menu-forms"),
    STUDENT_FORM("submenu-student-form"),

    ALERT_FRAME_AND_WINDOWS("menu-alerts"),
    ALERT("submenu-alerts"),
    WINDOWS("submenu-browser-windows"),
    FRAME("submenu-frames"),
    NESTED_FRAME("submenu-netsted"),
    MODAL_DIALOG("submenu-modal-dialogs"),

    WIDGETS("menu-widgets"),
    SELECTED_MENU("submenu-select-menu"),
    TOOL_TIPS("submenu-tool-tip"),
    AUTO_COMPLETE("submenu-auto-complete"),

    ;

    final private String name;

    Menu(String name) {
        this.name = name;
    }
}