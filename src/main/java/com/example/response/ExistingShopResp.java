package com.example.response;

public class ExistingShopResp {
	private PreviousAddress PreviousAddress;

    private CurrentAddress CurrentAddress;

    public PreviousAddress getPreviousAddress ()
    {
        return PreviousAddress;
    }

    public void setPreviousAddress (PreviousAddress PreviousAddress)
    {
        this.PreviousAddress = PreviousAddress;
    }

    public CurrentAddress getCurrentAddress ()
    {
        return CurrentAddress;
    }

    public void setCurrentAddress (CurrentAddress CurrentAddress)
    {
        this.CurrentAddress = CurrentAddress;
    }
}
