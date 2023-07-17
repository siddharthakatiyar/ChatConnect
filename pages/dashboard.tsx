import { useContext } from "react";
import ConversationDetails from "../components/ConversationDetails";
import SideBar from "../components/SideBar";
import { ConversationContext } from "../context/ConversationContext";

export default function Home() {
  const { conversation } = useContext(ConversationContext);
  const IconHome = () => (
    <div className="flex flex-col w-full h-full items-center justify-center">
      <div className="flex flex-col items-center">
        <img src="/logo.png" alt="logo" className="w-24 h-24" />
      </div>
      <div className="flex flex-col items-center mt-10">
        <h1 className="text-[#161616] text-3xl font-extralight">Chat Connect</h1>
        <div className="flex flex-col mt-4 w-10/12 text-center text-[#8696a0] text-base font-light">
          <h2>Welcome to Chat Connect, your go-to messaging application for instant and seamless communication. Whether you're staying in touch with friends, collaborating with colleagues, or connecting with family members, Chat Connect offers a user-friendly platform to keep your conversations flowing effortlessly.</h2>
        </div>
        <div className="my-6 border-b-[1px] border-[rgba(134,150,160,0.15)] w-full">
        </div>
      </div>
      
    </div>
  )

  return (
    <div className="flex justify-center">
      <div className="flex w-full xl:container h-screen xl:py-4">
        <SideBar />
        <div className="flex w-[70%] bg-[#d9d9d9]">
          {
            conversation.username
              ? <ConversationDetails />
              : <IconHome />
          }
        </div>
      </div>
    </div>
    
  )
}