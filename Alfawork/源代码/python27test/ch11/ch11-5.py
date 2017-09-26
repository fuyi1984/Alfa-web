#!/usr/bin/env python
# -*- coding: UTF-8 -*-
import wx


class MyFrame(wx.Frame):
    def __init__(self, parent):
        wx.Frame.__init__(self, parent, -1, 'Hello World',
                          size=(300, 300))
        panel = wx.Panel(self)
        sizer = wx.BoxSizer(wx.VERTICAL)
        panel.SetSizer(sizer)

        tx = wx.StaticText(panel, -1, 'Hello world!')
        sizer.Add(tx, 0, wx.TOP | wx.LEFT, 100)

        self.Centre()


app = wx.App()
frame = MyFrame(None)
frame.Show(True)
app.MainLoop()
