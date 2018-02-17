preferences {
   section() {
       input "switches", "capability.switchLevel", multiple: true
   }
}

mappings {
    // e.g., GET <path>/switches
    path("/switches") {
        action: [
            GET: "getSwitches"
        ]
    }
    // e.g., GET <path>/switches/1234
    path("/switches/:id") {
        action: [
            GET: "getSwitch"
        ]
    }
    // e.g., POST <path>/switches/1234/80
    path("/switches/:id/:level") {
        action: [
            POST: "setSwitchLevel"
        ]
    }
}

// return a list of maps that has info about the configured switches
// will be serialized to JSON when sent to the client
def getSwitches() {
    def theSwitches = []
    theSwitches.each {
        theSwitches << [id: it.id, name: it.displayName, status: it.currentSwitch, level: it.currentLevel]
    }
    return theSwitches
}

// return a map that has info about the switch with the specified id.
// will be serialized to JSON when sent to the client
// 404 if no switch with specified id configured
def getSwitch() {
    def s = switches.find { it.id == params.id }
    if (s) {
        return [id: it.id, name: it.displayName, status: it.currentSwitch, level: it.currentLevel]
    } else {
        httpError(400, "No switch with id $params.id configured")
    }
}

// sets the switch with the specified id to the specified level
// 404 if no switch with specified id configured
def setSwitchLevel() {
    def d = switches.find { it.id == params.id }
    if (d) {
        d.setLevel(params.level)
    } else {
        httpError(400, "No switch with $id authorized")
    }
}