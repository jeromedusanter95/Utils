package com.jeromedusanter.utils

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager

class ConnectToWifiReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        println("***************************")
        println("***************************")
        println("onReceive")
        println("***************************")
        println("***************************")

        context?.let {
            println("context is not null")
            val wifiManager =
                context.applicationContext.getSystemService(Activity.WIFI_SERVICE) as WifiManager
            wifiManager.isWifiEnabled = true
            oldSolution(wifiManager, SSID, PASSWORD)
        }
    }

    private fun oldSolution(wifiManager: WifiManager, ssid: String, password: String) {
        val wifiConfig = WifiConfiguration()
        wifiConfig.SSID = String.format("\"%s\"", ssid)
        wifiConfig.preSharedKey = String.format("\"%s\"", password)

        val netId = wifiManager.addNetwork(wifiConfig)
        wifiManager.disconnect()
        wifiManager.enableNetwork(netId, true)
        wifiManager.reconnect()
    }

    companion object {
        private const val SSID = "Weblib-Office"
        private const val PASSWORD = "34avinaigriers"
    }
}