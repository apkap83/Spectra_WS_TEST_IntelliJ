package gr.wind.spectra.business;

public enum DBTable {
    AGCF_Resource_Path("AGCF_Resource_Path"),
    All_Elements_Slots("All_Elements_Slots"),
    CLI_Association("CLI_Association"),
    Cli_Mappings("Cli_Mappings"),
    Data_Resource_Path("Data_Resource_Path"),
    DateOfReport("DateOfReport"),
    Derived_LLU_Big_Data("Derived_LLU_Big_Data"),
    FTTCNetworkElementHierarchy("FTTCNetworkElementHierarchy"),
    FTTC_Big_Data_Selection("FTTC_Big_Data_Selection"),
    FTTC_Location_Element_Slot("FTTC_Location_Element_Slot"),
    FTTH_Big_Data_Selection("FTTH_Big_Data_Selection"),
    FTTH_NetworkElementHierarchy("FTTH_NetworkElementHierarchy"),
    FTTX_NetworkElementHierarchy("FTTX_NetworkElementHierarchy"),
    HierarchyTablePerTechnology2("HierarchyTablePerTechnology2"),
    IPBB_NetworkElementHierarchy("IPBB_NetworkElementHierarchy"),
    LLINV_Site_Topology_Report("LLINV_Site_Topology_Report"),
    LLU_All_NetworkElementHierarchy("LLU_All_NetworkElementHierarchy"),
    LLU_Big_Data_Selection("LLU_Big_Data_Selection"),
    LLU_Concentrators("LLU_Concentrators"),
    LLU_Data_NetworkElementHierarchy("LLU_Data_NetworkElementHierarchy"),
    LLU_Location_Element_Slot("LLU_Location_Element_Slot"),
    LLU_Voice_NetworkElementHierarchy("LLU_Voice_NetworkElementHierarchy"),
    LOCATION_PER_ACCESS_TYPE("LOCATION_PER_ACCESS_TYPE"),
    Location_Element_Slot("Location_Element_Slot"),
    NGA_CLIS("NGA_CLIS"),
    NGA_Lines("NGA_Lines"),
    OTE_KV_Symefs("OTE_KV_Symefs"),
    Prov_IPTV_Resource_Path("Prov_IPTV_Resource_Path"),
    Prov_Internet_Resource_Path("Prov_Internet_Resource_Path"),
    Prov_Voice_Resource_Path("Prov_Voice_Resource_Path"),
    Sub_Sum_BRAS("Sub_Sum_BRAS"),
    Sub_Sum_FTTH("Sub_Sum_FTTH"),
    Sub_Sum_LLU_FTTC("Sub_Sum_LLU_FTTC"),
    TopologiesAndServices("TopologiesAndServices"),
    VF_KV_Symefs("VF_KV_Symefs"),
    WHOLESALE_Data_NetworkElementHierarchy("WHOLESALE_Data_NetworkElementHierarchy"),
    WHOLESALE_FTTC_NetworkElementHierarchy("WHOLESALE_FTTC_NetworkElementHierarchy"),
    WHOLESALE_FTTH_NetworkElementHierarchy("WHOLESALE_FTTH_NetworkElementHierarchy"),
    WIND_KV_Symefs("WIND_KV_Symefs"),
    Wholesale_Concentrators("Wholesale_Concentrators"),
    Wind_NGA_Concentrators("Wind_NGA_Concentrators");

    public final String tableName;

    private DBTable(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return tableName;
    }
}
