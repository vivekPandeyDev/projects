import { useOutletContext } from "react-router-dom"
import { VanContextType } from "../../interface/props"


const HostVanInfo = () => {
  const {van} =  useOutletContext<VanContextType>()
  return (
    <div>HostVanInfo {van.name}</div>
  )
}

export default HostVanInfo