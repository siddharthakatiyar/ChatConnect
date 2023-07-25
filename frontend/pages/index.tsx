import { useContext } from "react";
import ConversationDetails from "../components/ConversationDetails";
import SideBar from "../components/SideBar";
import { ConversationContext } from "../context/ConversationContext";

export default function Home() {
  const { conversation } = useContext(ConversationContext);
  const IconHome = () => (
    <div className="flex flex-col w-full h-full items-center justify-center">
      <div className="flex flex-col items-center mt-10">
        <img src="/logo.png" alt="logo" className="w-24 h-24" />
        <h1 className="text-[#161616] text-3xl font-extralight">Chat Connect</h1>
        <div className="flex flex-col mt-4 w-10/12 text-center text-[#3f484d] text-base font-light">
          <h2>Welcome to Chat Connect, your go-to messaging application for instant and seamless communication. Whether you are staying in touch with friends, collaborating with colleagues, or connecting with family members, Chat Connect offers a user-friendly platform to keep your conversations flowing effortlessly.</h2>
        </div>
      </div>
      
    </div>
  )

  return (
      <div className="flex w-full xl:container h-screen xl:py-4 justify-center">
        <SideBar />
        <div className="flex w-[70%]" style={{backgroundImage: "url('/background.jpg')", backgroundSize: "cover", backgroundPosition: "center"}}>
          {
            conversation.username
              ? <ConversationDetails />
              : <IconHome />
          }
        </div>
      </div>
  )
}