/**
 * 
 *
 
 *
 */

 preferences {

    input("deviceId", "text", title: "Device ID")

    input("token", "text", title: "Access Token")

}

 
    metadata {
	   definition (name: "Kitchen Spark/Photon WS2812b", namespace: "", author: "Justin Wurth") 
		{capability "Switch"
         capability "Color Control"
         capability "Notification"
	}
}

	// tile definitions
	
command "dim"

command "red"

command "blue"

command "holiday"
	

tiles {
		standardTile("switch", "device.switch", width: 2, height: 2, canChangeIcon: false) {
			
	state "off", label: '${name}', action: "switch.on", icon: "st.Lighting.light13", backgroundColor: "#ffffff"
			
	state "on", label: '${name}', action: "switch.off", icon: "st.Lighting.light13", backgroundColor: "#79b821", iconColor: "#ffffff"
		
}

		
standardTile("dim", "device.dim") {

	state "dim", label: '${name}', action: "dim", icon: "st.Lighting.light13", backgroundColor: "#d3d3d3", iconColor: "#ffffff"
}


     	
standardTile("red", "device.red") {
			
	state "red", label: '${name}', action: "red", icon: "st.Lighting.light13", backgroundColor: "#ff0000", iconColor: "#ff0000"
		
}

standardTile("blue", "device.blue") {
			
        state "blue", label: '${name}', action: "blue", icon: "st.Lighting.light13", backgroundColor: "#d3ffd3", iconColor: "#0000ff"
}

standardTile("holiday", "device.holiday") {
			
        state "holiday", label: '${name}', action: "holiday", icon: "st.Lighting.light13", backgroundColor: "#d3ffd3",  iconColor: "#ffffff"
}

       

standardTile("color", "device.color") {

state "dim", label: '${name}', action: "red", icon: "st.lighting.light13", backgroundColor:  "#d3d3d3", iconColor: "#ffffff"
       	
state "red", label: '${name}', action: "blue", icon: "st.Lighting.light13", backgroundColor: "#ff0000", iconColor: "#ffffff"
       
state "blue", label: '${name}', action: "holiday", icon: "st.Lighting.light13", backgroundColor: "#0000ff", iconColor: "#ffffff"
 
state "holiday", label: '${name}', action: "dim", icon: "st.Lighting.light13", backgroundColor: "#ffffff", iconColor: "#ffffff"
}
main(["switch","color","dim","red","blue","holiday"])   
details(["switch","color","dim","red","blue","holiday"])
	
}




def parse(String description) {
	log.error "This device does not support incoming events"
	return null
}
def off(){
	put '0'
}

def on() {
	put '1'
}

def dim() {
	put '2'
}

def red() {
	put '3'
}


def blue() {
	put '4'
}

def holiday() {
        put '5'
}

private put(led) {

    //Spark Core API Call

    httpPost(

        uri: "https://api.particle.io/v1/devices/${deviceId}/led",

        body: [access_token: token, command: led],  

    )

}