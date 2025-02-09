package com.testek.projects.common;

/**
 * Initializes the constants or common variables
 */
public class BaseConst {

    //region DYNAMIC BASE LOCATOR

    //region ComboBox
    public static String DYNAMIC_INPUT_TYPE_FORM = "//input[@type='%s']";
    public static String DYNAMIC_INPUT_NAME_FORM = "//input[@name='%s']";
    public static String DYNAMIC_BUTTON_TEXT_FORM = "//button[@type='%s']";
    public static String XPATH_DYNAMIC_FORM_CHECKBOX_INPUT = "";
    // endregion

    //region Checkbox
    public static String CHECKBOX_OK = "OK";
    //endregion


    //endregion

    /**
     * Create a filter option for comparison
     */
    public enum FilterOption {
        EQUAL("opt_equal"), NOT_EQUAL("opt_not_equal"), CONTAIN("opt_contain"), NOT_CONTAIN("opt_not_contain"), EMPTY("opt_empty"), FILLED("opt_filled"), SMALLER("opt_smaller"), SMALLER_EQUAL("opt_smaller_equal"), GREATER("opt_greater"), GREATER_EQUAL("opt_greater_equal"), START_WITH("opt_start_with"), END_WITH("opt_end_with");

        private final String name;

        private FilterOption(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
